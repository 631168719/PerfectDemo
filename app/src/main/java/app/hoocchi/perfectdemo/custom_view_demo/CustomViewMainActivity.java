package app.hoocchi.perfectdemo.custom_view_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.hoocchi.perfectdemo.R;

/**
 * 绘图部分API不支持硬件加速，关闭硬件加速的方式：
 *
 *  1. application级别： <application android:accelerated="false"></>
 *  2. activity级别：    <activity android:accelerated="false"></>
 *  3. window级别：     getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED ,
 *                          WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
 *  4. view级别： setLayerType(LAYER_TYPE_SOFTWARE , null)或 android:layerType="software"
 *
 *  判断当前是否正在使用硬件加速方式：
 *   canvas.isHardwareAccelerated();
 *   view.isHapticFeedbackEnabled();
 *
 */
public class CustomViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_main);
        setTitle("CustomView Demo");
    }


}
