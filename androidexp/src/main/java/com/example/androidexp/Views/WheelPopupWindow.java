package com.example.androidexp.Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidexp.R;
import com.example.androidexp.Views.WheelView.LoopView;
import com.example.androidexp.Views.WheelView.OnItemSelectedListener;

import java.util.List;

public class WheelPopupWindow extends PopupWindow{

	private View view;

	private TextView tvConfirm;

	private RelativeLayout rootview;
	private RelativeLayout.LayoutParams layoutParams;

	public WheelPopupWindow(Activity context, List<String> list, OnItemSelectedListener listener, View.OnClickListener clickListener) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.wheel_dialog, null);

		tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
		tvConfirm.setOnClickListener(clickListener);

		layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		rootview = (RelativeLayout) view.findViewById(R.id.rootview);

		// 设置PopupWindow的View
		this.setContentView(view);
		// 设置PopupWindow弹出窗体的宽
		this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		// 设置PopupWindow弹出窗体的高
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		// 设置PopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置PopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimBottom);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		// view添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		view.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = rootview.getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

		LoopView loopView = new LoopView(context);
		//设置是否循环播放
		loopView.setNotLoop();
		//滚动监听
		loopView.setListener(listener);
		//设置原始数据
		loopView.setItems(list);
		//设置初始位置
		loopView.setInitPosition(0);
		//设置字体大小
		loopView.setTextSize(20);
		rootview.addView(loopView, layoutParams);
	}
}
