package com.aahl.hl_letter.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.aahl.hl_letter.base.BaseApplication;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description : 界面相关的工具类
 */


public class UIUtils {

    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    static public int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** 获取上下文 */
    public static Context getContext() {
        return BaseApplication.getInstance();
    }

    /** px转换dip */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /** 获取Resources */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /** 获取字符数组 */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /** 根据id获取颜色color */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /** 根据id获取尺寸 */
    public static int getDimens(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    /** 获取view的id */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    /** 根据id获取资源文件里面的String */
    public static String getString(int id) {
        return getResources().getString(id);
    }

    /** 根据id获取资源文件里面的Drawable */
    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }


    /**
     * 获取全局handler
     */
    private static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * handler移除一个执行的对象
     */
    public static void removeCallBacks(Runnable r) {
        getHandler().removeCallbacks(r);
    }

    /**
     * handler延迟执行
     */
    public static void postDelay(Runnable runnable, long delay) {
        Handler handler = getHandler();
        handler.postDelayed(runnable, delay);
    }


}
