package com.aahl.sdk_utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author : Mr.Hao
 * @date :  2018/6/20
 * @description : Dialog工具类提交
 */

public class DialogUtils {

    /**
     * 系统默认等待框 点击外围消失
     */
    public static Dialog createProgressDialog(Context context) {
        return createProgressDialog(context, true);
    }

    /**
     * 系统默认等待框  可以控制外围点击是否消失
     */
    public static Dialog createProgressDialog(Context context, boolean needCancle) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(needCancle);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * 系统默认确定框  带取消和确定
     */
    public static Dialog showCommonDialog(Context context, String message,
                                          DialogInterface.OnClickListener listener) {
        return showCommonDialog(context, message, context.getString(R.string.dialog_positive),
                context.getString(R.string.dialog_negative), listener);
    }

    public static Dialog showCommonDialog(Context context, String message, String positiveText,
                                          String negativeText, DialogInterface.OnClickListener
                                                  listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positiveText, listener)
                .setNegativeButton(negativeText, null)
                .show();
    }

    /**
     * 系统默认确定框  只有确定
     */
    public static Dialog showConfirmDialog(Context context, String message,
                                           DialogInterface.OnClickListener listener) {
        return showConfirmDialog(context, message, context.getString(R.string.dialog_positive),
                listener);
    }

    public static Dialog showConfirmDialog(Context context, String message, String positiveText,
                                           DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positiveText, listener)
                .show();
    }
}
