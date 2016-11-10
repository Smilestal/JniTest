package com.example.androidexp.Utils;

import android.content.Context;

public class ContextUtils {
	private Context mContext;
	private static ContextUtils sHolder = null;
	
	public static void initContextHolder(Context context) {
		sHolder = new ContextUtils(context);
	}
	
	private ContextUtils(Context context) {
		mContext = context;
	}

	public static void setContext(Context context) {
		sHolder.mContext = context;
	}
	
	public static Context getContext() {
		return sHolder.mContext;
	}
	
}
