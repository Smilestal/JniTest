package com.example.androidexp.Utils;

import android.util.Log;

import com.example.androidexp.Constants.InnerContacts;

/**
 * Log工具类 d e i v w五种打印 Log功能默认关闭。需要手动更改DEBUG的值
 * 
 * @author KEZHUANG
 */
public class LogUtils {
	/**
	 * Log的开关<br>
	 * true为开启<br>
	 * false为关闭<br>
	 */
	public static boolean DEBUG = !InnerContacts.DEFAULT_DEBUG;

	/**
	 * Log 输出标签
	 */
	public static String TAG = InnerContacts.DEFAULT_TAG;

	public static void i(Object object) {
		if (DEBUG) {
			if (object == null) {
				i("标签" + TAG + "的打印内容为空！");
			}
			Log.i(TAG, object.toString());
		}
	}

	public static void d(Object object) {
		if (DEBUG) {
			if (object == null) {
				d("标签" + TAG + "的打印内容为空！");
			}
			Log.d(TAG, object.toString());
		}
	}

	public static void e(Object object) {
		if (DEBUG) {
			if (object == null) {
				e("标签" + TAG + "的打印内容为空！");
			}
			Log.e(TAG, object.toString());
		}
	}

	public static void v(Object object) {
		if (DEBUG) {
			if (object == null) {
				v("标签" + TAG + "的打印内容为空！");
			}
			Log.v(TAG, object.toString());
		}
	}

	public static void w(Object object) {
		if (DEBUG) {
			if (object == null) {
				w("标签" + TAG + "的打印内容为空！");
			}
			Log.w(TAG, object.toString());
		}
	}
	
	public static void inner_i(Object object,boolean DEBUG){
		if(DEBUG){
			i(object);
		}
	}
	
	public static void inner_e(Object object,boolean isShow){
		if(isShow){
			e(object);
		}
	}
	
	public static void inner_d(Object object,boolean DEBUG){
		if(DEBUG){
			d(object);
		}
	}
}
