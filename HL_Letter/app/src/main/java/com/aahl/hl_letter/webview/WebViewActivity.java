package com.aahl.hl_letter.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.jsbridge.BridgeHandler;
import com.aahl.hl_letter.jsbridge.BridgeWebView;
import com.aahl.hl_letter.jsbridge.CallBackFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.Hao on 2018/5/9
 */
public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    BridgeWebView mWebview;

    private String url = "http://ymr-h5.vpclub.cn/html/index.html#/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        initListener();

        // 禁止 file 协议加载 JavaScript
        if (url.startsWith("file://")) {//用子类的url（此处是防止报错）
            mWebview.getSettings().setJavaScriptEnabled(false);
        } else {// 设置是否允许 WebView 使用 JavaScript（默认是不允许）
            mWebview.getSettings().setJavaScriptEnabled(true);
        }
        registerJsBridge();
        mWebview.loadUrl(url);

//        // Android版本变量
//        final int version = Build.VERSION.SDK_INT;
//        // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
//        if (version < Build.VERSION_CODES.KITKAT) {
//            mWebview.loadUrl(url);
//        } else {
//            mWebview.evaluateJavascript(url, new ValueCallback<String>() {
//                @Override
//                public void onReceiveValue(String s) {
//                    //此处为 js 返回的结果
//                }
//            });
//
//        }
    }


    /**
     * js桥
     */
    private void registerJsBridge() {
        mWebview.registerHandler("scanQR", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i("TEST", "===========");
                CallBackFunction function1 = function;
//                autoObtainCameraPermission();
            }
        });
    }


    private void initListener() {
//        mWebview.setWebViewClient(new SafeWebViewClient());
        mWebview.setWebChromeClient(new SafeWebChromeClient());

    }

    public class SafeWebViewClient extends WebViewClient {

        /**
         * 当WebView得页面Scale值发生改变时回调
         */
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
        }

        /**
         * 是否在 WebView 内加载页面
         *
         * @param view
         * @param url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        /**
         * WebView 开始加载页面时回调，一次Frame加载对应一次回调
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        /**
         * WebView 完成加载页面时回调，一次Frame加载对应一次回调
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        /**
         * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。
         *
         * @param view WebView
         * @param url  url
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        /**
         * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
         *
         * @param view    WebView
         * @param request 当前产生 request 请求
         * @return WebResourceResponse
         */
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        /**
         * WebView 访问 url 出错
         *
         * @param view
         * @param request
         * @param error
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        /**
         * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
         *
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }
    }

    public class SafeWebChromeClient extends WebChromeClient {

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        /**
         * 当前 WebView 加载网页进度
         *
         * @param view
         * @param newProgress
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        /**
         * Js 中调用 alert() 函数，产生的对话框
         *
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        /**
         * 处理 Js 中的 Confirm 对话框
         *
         * @param view
         * @param url
         * @param message
         * @param result
         * @return
         */
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 处理 JS 中的 Prompt对话框
         *
         * @param view
         * @param url
         * @param message
         * @param defaultValue
         * @param result
         * @return
         */
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        /**
         * 接收web页面的icon
         *
         * @param view
         * @param icon
         */
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        /**
         * 接收web页面的 Title
         *
         * @param view
         * @param title
         */
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    }
}