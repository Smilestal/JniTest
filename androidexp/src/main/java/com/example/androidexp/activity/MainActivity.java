package com.example.androidexp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.androidexp.R;
import com.example.androidexp.Utils.ToastUtils;
import com.example.androidexp.Views.CustomDialog;
import com.example.androidexp.Views.SwitchView;
import com.example.androidexp.Views.WheelPopupWindow;
import com.example.androidexp.Views.WheelView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btn_drag;
    Button btn_custom;
    Button btn_wheel;
    Button btn_webView;
    Button btn_navigation;
    SwitchView view_switch;

    private int selectIndex = 0;
    private WheelPopupWindow wheelWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_drag = (Button) findViewById(R.id.btn_drag);
        btn_drag.setOnClickListener(this);
        btn_custom = (Button) findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(this);
        btn_wheel = (Button) findViewById(R.id.btn_wheel);
        btn_wheel.setOnClickListener(this);
        btn_webView = (Button) findViewById(R.id.btn_webView);
        btn_webView.setOnClickListener(this);
        btn_navigation = (Button) findViewById(R.id.btn_navigation);
        btn_navigation.setOnClickListener(this);

        view_switch = (SwitchView) findViewById(R.id.view_switch);
        view_switch.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(View view) {
                view_switch.toggleSwitch(true);
                ToastUtils.show(MainActivity.this, "toggleToOn");
            }

            @Override
            public void toggleToOff(View view) {
                view_switch.toggleSwitch(false);
                ToastUtils.show(MainActivity.this, "toggleToOff");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_drag:
                intent = new Intent(this, DragActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_webView:
                intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_navigation:
                intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_custom:
                new CustomDialog.Builder(this)
                        .setTitle("温馨提示")
                        .setMessage("提示内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        ToastUtils.show(MainActivity.this, "1", "2", "3", "4");
                                    }

                                })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }

                                })
                        .create()
                        .show();
                break;

            case R.id.btn_wheel:
                final List<String> list = new ArrayList<String>();
                list.add("顺丰");
                list.add("圆通速递");
                list.add("中通速递");
                list.add("申通");
                list.add("百世汇通");
                list.add("韵达快运");
                list.add("德邦物流");
                list.add("天天快递");
                list.add("全峰快递");
                list.add("宅急送");
                list.add("EMS");
                list.add("DHL");

                selectIndex = 0;
                // 实例化SelectPicPopupWindow
                wheelWindow = new WheelPopupWindow(this, list, new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        selectIndex = index;
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.show(MainActivity.this, list.get(selectIndex));
                        wheelWindow.dismiss();
                    }
                });
                // 设置layout在PopupWindow中显示的位置
                wheelWindow.showAtLocation(findViewById(R.id.view_main),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) btn_wheel.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_wheel.getWindowToken(), 0);
                break;
        }
    }
}
