package com.example.androidexp.Utils;

import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 倒计时计时器
 *
 * @author POST
 */
public class TimeCountdown extends CountDownTimer {

    private TextView countdownTextView;
    private LinearLayout countdownll;
    private int color;

    public TimeCountdown(long millisInFuture, long countDownInterval, TextView countdownTextView, LinearLayout countdowll) {
        super(millisInFuture, countDownInterval);
        this.countdownll = countdowll;
        this.countdownTextView = countdownTextView;
//		this.color = countdownTextView.getCurrentTextColor();
    }

    @Override
    public void onFinish() {   // 计时完毕时触发
        countdownTextView.setText("获取验证码");
//		countdownTextView.setTextColor(/*Color.parseColor("#FFFFFF")*/color);
        countdownll.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {   // 计时过程显示
        countdownll.setClickable(false);
//		countdownTextView.setTextColor(Color.parseColor("#4A4A4A"));
        countdownTextView.setText(millisUntilFinished / 1000 + " 秒");
    }

}
