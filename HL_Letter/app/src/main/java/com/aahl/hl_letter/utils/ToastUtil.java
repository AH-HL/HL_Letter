package com.aahl.hl_letter.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.aahl.hl_letter.base.BaseApplication;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description : Toast唯一工具类
 */


public class ToastUtil {
	private static Toast mToast = null;

	/**
	 * 直接传入String类型
	 * @param text  String
	 */
	public static void show(String text){
		show(text, Toast.LENGTH_SHORT);
	}

	/**
	 * 传入values-String类型的int值
	 * @param text  int
	 */
	public static void show(int text){
		show(text, Toast.LENGTH_SHORT);
	}


	/**以下是内部上面2个方法的内调（也可以使用，建议就用上面2种）*/
	private static void show(String text, int duration){
		show(BaseApplication.getInstance(),text, duration);
	}
	
	private static void show(int id, int duration){
		show(BaseApplication.getInstance(),id, duration);
	}
	
	private static void show(final Context context, final String text, final int duration) {
		if (text == null) {
			return;
		}
		Runnable toastRunnable = new Runnable() {
			@SuppressLint("ShowToast")
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(context, text, duration);
				} else {
					mToast.setText(text);
					mToast.setDuration(duration);
				}
				mToast.show();
			}
		};
		if (context instanceof Activity) {
		    Activity activity = (Activity) context;
			activity.runOnUiThread(toastRunnable);
		} else {
			Handler handler = new Handler(context.getMainLooper());
			handler.post(toastRunnable);
		}

	}
	
	private static void show(final Context context, final int id, final int duration) {
		if (id <= 0) {
			return;
		}
		Runnable toastRunnable = new Runnable() {
			@SuppressLint("ShowToast")
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(context, id, duration);
				} else {
					mToast.setText(id);
					mToast.setDuration(duration);
				}
				mToast.show();
			}
		};
		if (context instanceof Activity) {
			final Activity activity = (Activity) context;
			activity.runOnUiThread(toastRunnable);
		} else {
			Handler handler = new Handler(context.getMainLooper());
			handler.post(toastRunnable);
		}
	}


	public static void show(final Context context, final String text, final int duration, final int gravity) {

		if (text == null) {
			return;
		}
		Runnable toastRunnable = new Runnable() {
			@Override
			public void run() {
				// if (mToast == null) {
				mToast = Toast.makeText(context, text, duration);
				// } else {
				mToast.setText(text);
				mToast.setDuration(duration);
				// }
				mToast.setGravity(gravity, 0, 0);

				mToast.show();
			}
		};
		if (context instanceof Activity) {
			final Activity activity = (Activity) context;
			activity.runOnUiThread(toastRunnable);
		} else {
			Handler handler = new Handler(context.getMainLooper());
			handler.post(toastRunnable);
		}

	}
}
