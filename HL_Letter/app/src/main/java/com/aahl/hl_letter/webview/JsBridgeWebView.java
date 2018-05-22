package com.aahl.hl_letter.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebView;

import java.lang.reflect.Method;

/**
 * Created by Mr.Hao on 2018/5/9.
 */

public class JsBridgeWebView extends WebView {

    public JsBridgeWebView(Context context) {
        super(context);
        removeSearchBox();
    }

    public JsBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        removeSearchBox();
    }

    public JsBridgeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeSearchBox();
    }


    /**
     * 移除系统注入的对象，避免js漏洞
     * <p>
     * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-1939
     * https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2014-7224
     */
    private void removeSearchBox() {
        super.removeJavascriptInterface("searchBoxJavaBridge_");
        super.removeJavascriptInterface("accessibility");
        super.removeJavascriptInterface("accessibilityTraversal");
    }


    /**
     * 此问题在4.2.1和4.2.2比较集中，关闭辅助功能，google 下很多结果都是一样的。
     * 修复方法：在初始化 WebView 时调用disableAccessibility方法即可。
     *
     * @param context
     */
    public static void disableAccessibility(Context context) {
        if (Build.VERSION.SDK_INT == 17/*4.2 (Build.VERSION_CODES.JELLY_BEAN_MR1)*/) {
            if (context != null) {
                try {
                    AccessibilityManager am = (AccessibilityManager) context.getSystemService
                            (Context.ACCESSIBILITY_SERVICE);
                    if (!am.isEnabled()) {
                        //Not need to disable accessibility
                        return;
                    }

                    Method setState = am.getClass().getDeclaredMethod("setState", int.class);
                    setState.setAccessible(true);
                    setState.invoke(am, 0);
                    /**{@link AccessibilityManager#STATE_FLAG_ACCESSIBILITY_ENABLED}*/
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }
    }


    /**
     * 在创建 WebView 时崩溃，跟进栈信息，我们需要在 setOverScrollMode 方法上加异常保护处理
     *
     * @param mode
     */
    @Override
    public void setOverScrollMode(int mode) {

        //方法一
//        try {
//            super.setOverScrollMode(mode);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

        //方法二(方法二更全面)
        try {
            super.setOverScrollMode(mode);
        } catch (Throwable e) {
            String trace = Log.getStackTraceString(e);
            if (trace.contains("android.content.pm.PackageManager$NameNotFoundException") ||
                    trace.contains("java.lang.RuntimeException: Cannot load WebView") || trace
                    .contains("android.webkit.WebViewFactory$MissingWebViewPackageException: " +
                            "Failed to load WebView provider: No WebView installed")) {
                e.printStackTrace();
            } else {
                throw e;
            }
        }
    }

    @Override
    public boolean isPrivateBrowsingEnabled() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && getSettings()
                == null) {
            return false;
        } else {
            return super.isPrivateBrowsingEnabled();
        }
    }
}
