package com.renwen.sensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener{

    private TextView tvProximity;
    private TextView tvTemperature;
    private TextView tvPressure;
    private TextView tvLight;
    private TextView tvHumidity;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        tvProximity = (TextView) findViewById(R.id.tv_proximity);
        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
        tvPressure = (TextView) findViewById(R.id.tv_pressure);
        tvLight = (TextView) findViewById(R.id.tv_light);
        tvHumidity = (TextView) findViewById(R.id.tv_humidity);
        //获取传感器SensorManager对象
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册临近传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_UI);

        //注册温度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_UI);

        //注册压力传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_UI);

        //注册光线传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);

        //注册湿度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),
                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

//    方向传感器：
//    SensorEvent.values[0]：绕着Z轴旋转的角度。如果Y轴（正常拿手机的方向）正对着北方，该值是0，如果Y轴指向南方，改值是180，Y轴指向东方，该值是90，如果Y轴指向西方，该值是270。
//    SensorEvent.values[1]：绕着X轴旋转的度数。当从Z轴正方向朝向Y轴正方向，改值为正值。反之，为负值。该值在180至-180之间变动。
//    SensorEvent.values[2]：绕着Y轴旋转的度数。当从Z轴正方向朝向X轴正方向，改值为正值。反之，为负值。该值在180至-180之间变动。

//    磁场传感器：
//    SensorEvent.values[0]：沿着X轴的磁力(μT，millitesla）
//    SensorEvent.values[1]：沿着Y轴的磁力(μT，millitesla）
//    SensorEvent.values[2]：沿着Y轴的磁力(μT，millitesla）
    @Override
    public void onSensorChanged(SensorEvent event) {
        //判断传感器类别
        switch (event.sensor.getType()) {
            //SensorEvent.values[0]：手机正面距离邻近物理的距离（CM）
            case Sensor.TYPE_PROXIMITY://临近传感器
                tvProximity.setText(String.valueOf(event.values[0]));
                break;

            case Sensor.TYPE_AMBIENT_TEMPERATURE://温度传感器
                tvTemperature.setText("温度："+String.valueOf(event.values[0])+"°C");
                break;

            case Sensor.TYPE_PRESSURE://压力传感器
                tvPressure.setText("压力："+String.valueOf(event.values[0])+"hPa");
                break;

            case Sensor.TYPE_LIGHT://光线传感器
                tvLight.setText("光线："+String.valueOf(event.values[0])+"lx");
                break;

            case Sensor.TYPE_RELATIVE_HUMIDITY://湿度传感器
                tvHumidity.setText("相对湿度："+String.valueOf(event.values[0])+"RH（%）");
                break;
            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
