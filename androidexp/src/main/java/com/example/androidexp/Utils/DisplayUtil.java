package com.example.androidexp.Utils;

import android.content.Context;
import android.util.TypedValue;

public class DisplayUtil {

	/**
	 * dp转px
	 */
	public static int dpToPx(Context context, float dpVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources()
						.getDisplayMetrics());
		return result;
	}

	/**
	 * sp转px
	 */
	public static int spToPx(Context context, float spVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources()
						.getDisplayMetrics());
		return result;
	}

	/**
	 * px转dp
	 */
	public static int pxToDp(Context context, float pxVal) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int result = (int) (pxVal / scale);
		return result;
	}

	/**
	 * px转sp
	 */
	public static float pxToSp(Context context, float pxVal) {
		int result = (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
		return result;
	}
	
}
