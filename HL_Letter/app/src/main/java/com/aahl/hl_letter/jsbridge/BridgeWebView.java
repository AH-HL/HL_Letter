package com.aahl.hl_letter.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("SetJavaScriptEnabled")
public class BridgeWebView extends WebView implements LvUJsBridge {

    private final String TAG = "BridgeWebView";
    public static final String toLoadJs = "LvUJsBridge.js";
    Map<String, CallBackFunction> responseCallbacks = new HashMap<String, CallBackFunction>();
    Map<String, BridgeHandler> messageHandlers = new HashMap<String, BridgeHandler>();
    BridgeHandler defaultHandler = new DefaultHandler();

    private List<Message> startupMessage = new ArrayList<Message>();

    public List<Message> getStartupMessage() {
        return startupMessage;
    }

    public void setStartupMessage(List<Message> startupMessage) {
        this.startupMessage = startupMessage;
    }

    private long uniqueId = 0;

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BridgeWebView(Context context) {
        super(context);
        init();
    }

    /**
     * @param handler default handler,handle messages send by js without assigned handler name,
     *                if js message has handler name, it will be handled by named handlers registered by native
     */
    public void setDefaultHandler(BridgeHandler handler) {
        this.defaultHandler = handler;
    }

    private void init() {


        /** --------------  WebView中，主要漏洞有三类： start --------------------*/

        /**
         *  一、任意代码执行漏洞
         *
         *WebView 中 addJavascriptInterface（） 接口
         *WebView 内置导出的 searchBoxJavaBridge_对象
         *WebView 内置导出的 accessibility 和 accessibilityTraversalObject 对象
         */

        /**
         *  二、密码明文存储漏洞
         *
         *  设置this.getSettings().setSavePassword(false);//关闭密码保存提醒
         */

        /**
         *  三、域控制不严格漏洞
         *
         *  file域
         */



        /** --------------  file域 协议漏洞处理 start --------------------*/

        /**禁用 file 协议；*/
        /*this.getSettings().setAllowFileAccess(false);
        this.getSettings().setAllowFileAccessFromFileURLs(false);
		this.getSettings().setAllowUniversalAccessFromFileURLs(false);*/

        /**
         * 对于需要使用 file 协议的应用，禁止 file 协议加载 JavaScript。
         *
         * 设置this.getSettings().setJavaScriptEnabled(true)时
         * 通过 javascript 的延时执行和将当前文件替换成指向其它文件的软链接就可以读取到被符号链接所指的文件
         */
        // 需要使用 file 协议
        this.getSettings().setAllowFileAccess(true);
        this.getSettings().setAllowFileAccessFromFileURLs(false);
        this.getSettings().setAllowUniversalAccessFromFileURLs(false);
        // 禁止 file 协议加载 JavaScript
		/*if (url.startsWith("file://")) {//用子类的url
			this.getSettings().setJavaScriptEnabled(false);
		} else {// 设置是否允许 WebView 使用 JavaScript（默认是不允许）
			this.getSettings().setJavaScriptEnabled(true);
		}*/

        /** --------------  file域 协议漏洞处理 end  --------------------*/

        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setDomStorageEnabled(true);
//        this.getSettings().setJavaScriptEnabled(true);// 设置与Js交互的权限
        this.getSettings().setTextZoom(100);
        this.getSettings().setSavePassword(false);//关闭密码保存提醒
        this.setDefaultHandler(new DefaultHandler());
        removeSearchBox();//解决webwiew第一点漏洞中的内置导出
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.setWebViewClient(generateBridgeWebViewClient());
    }

    /**
     * 移除系统注入的对象，避免js漏洞
     * 在Android 3.0以下，Android系统会默认通过searchBoxJavaBridge_ ,accessibility ,accessibilityTraversal的Js接口
     * 给 WebView 添加一个JS映射对象：searchBoxJavaBridge_对象
     *
     * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-1939
     * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-7224
     */
    private void removeSearchBox() {
        super.removeJavascriptInterface("searchBoxJavaBridge_");
        super.removeJavascriptInterface("accessibility");
        super.removeJavascriptInterface("accessibilityTraversal");
    }

    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }

    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     */
    void handlerReturnData(String url) {
        String functionName = BridgeUtil.getFunctionFromReturnUrl(url);
        CallBackFunction f = responseCallbacks.get(functionName);
        String data = BridgeUtil.getDataFromReturnUrl(url);
        if (f != null) {
            f.onCallBack(data);
            responseCallbacks.remove(functionName);
            return;
        }
    }

    @Override
    public void send(String data) {
        send(data, null);
    }

    @Override
    public void send(String data, CallBackFunction responseCallback) {
        doSend(null, data, responseCallback);
    }

    /**
     * 保存message到消息队列
     *
     * @param handlerName      handlerName
     * @param data             data
     * @param responseCallback CallBackFunction
     */
    private void doSend(String handlerName, String data, CallBackFunction responseCallback) {
        Message m = new Message();
        if (!TextUtils.isEmpty(data)) {
            m.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(BridgeUtil.CALLBACK_ID_FORMAT, ++uniqueId + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            m.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.setHandlerName(handlerName);
        }
        queueMessage(m);
    }

    /**
     * list<message> != null 添加到消息集合否则分发消息
     *
     * @param m Message
     */
    private void queueMessage(Message m) {
        if (startupMessage != null) {
            startupMessage.add(m);
        } else {
            dispatchMessage(m);
        }
    }

    /**
     * 分发message 必须在主线程才分发成功
     *
     * @param m Message
     */
    void dispatchMessage(Message m) {
        String messageJson = m.toJson();
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson.replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2");
        messageJson = messageJson.replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson);
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            this.loadUrl(javascriptCommand);
        }
    }

    /**
     * 刷新消息队列
     */
    void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, new CallBackFunction() {

                @Override
                public void onCallBack(String data) {
                    // deserializeMessage 反序列化消息
                    List<Message> list = null;
                    try {
                        list = Message.toArrayList(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    if (list == null || list.size() == 0) {
                        return;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        Message m = list.get(i);
                        String responseId = m.getResponseId();
                        // 是否是response  CallBackFunction
                        if (!TextUtils.isEmpty(responseId)) {
                            CallBackFunction function = responseCallbacks.get(responseId);
                            String responseData = m.getResponseData();
                            function.onCallBack(responseData);
                            responseCallbacks.remove(responseId);
                        } else {
                            CallBackFunction responseFunction = null;
                            // if had callbackId 如果有回调Id
                            final String callbackId = m.getCallbackId();
                            if (!TextUtils.isEmpty(callbackId)) {
                                responseFunction = new CallBackFunction() {
                                    @Override
                                    public void onCallBack(String data) {
                                        Message responseMsg = new Message();
                                        responseMsg.setResponseId(callbackId);
                                        responseMsg.setResponseData(data);
                                        queueMessage(responseMsg);
                                    }
                                };
                            } else {
                                responseFunction = new CallBackFunction() {
                                    @Override
                                    public void onCallBack(String data) {
                                        // do nothing
                                    }
                                };
                            }
                            // BridgeHandler执行
                            BridgeHandler handler;
                            if (!TextUtils.isEmpty(m.getHandlerName())) {
                                handler = messageHandlers.get(m.getHandlerName());
                            } else {
                                handler = defaultHandler;
                            }
                            if (handler != null) {
                                handler.handler(m.getData(), responseFunction);
                            }
                        }
                    }
                }
            });
        }
    }


    public void loadUrl(String jsUrl, CallBackFunction returnCallback) {
        this.loadUrl(jsUrl);
        // 添加至 Map<String, CallBackFunction>
        responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl), returnCallback);
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlerName handlerName
     * @param handler     BridgeHandler
     */
    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            messageHandlers.put(handlerName, handler);
        }
    }

    /**
     * unregister handler
     */
    public void unregisterHandler(String handlerName) {
        if (handlerName != null) {
            messageHandlers.remove(handlerName);
        }
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     *
     * @param handlerName handlerName
     * @param data        data
     * @param callBack    CallBackFunction
     */
    public void callHandler(String handlerName, String data, CallBackFunction callBack) {
        doSend(handlerName, data, callBack);
    }
}
