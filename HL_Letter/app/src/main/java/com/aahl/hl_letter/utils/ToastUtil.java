package com.aahl.hl_letter.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.aahl.hl_letter.base.BaseApplication;

public class ToastUtil {
	private static final String TAG = "ToastUtil";
	private static Toast mToast = null;

	public static void show(String text){
		show(text, Toast.LENGTH_SHORT);
	}
	
	public static void show(int text){
		show(text, Toast.LENGTH_SHORT);
	}
	
	public static void show(String text, int duration){
		show(BaseApplication.getInstance(),text, duration);
	}
	
	public static void show(int id,  int duration){
		show(BaseApplication.getInstance(),id, duration);
	}
	
	public static void show(final Context context, final String text, final int duration) {
		if (text == null) {
			return;
		}
		Runnable toastRunnable = new Runnable() {
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
	
	public static void show(final Context context, final int id, final int duration) {
		if (id <= 0) {
			return;
		}
		Runnable toastRunnable = new Runnable() {
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
