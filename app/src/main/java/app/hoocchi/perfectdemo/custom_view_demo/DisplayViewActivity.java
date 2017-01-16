package app.hoocchi.perfectdemo.custom_view_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import app.hoocchi.perfectdemo.BaseActivity;
import app.hoocchi.perfectdemo.custom_view_demo.view.BasicGraphics;
import app.hoocchi.perfectdemo.custom_view_demo.view.BasicTexts;

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
public class DisplayViewActivity extends BaseActivity {

    public static final String TYPE = "type";
    public static final int GRAPHICS = 0 ;
    public static final int TEXTS = 1 ;

    public static void jump(Context context ,int type){
        Intent i = new Intent(context , DisplayViewActivity.class);
        i.putExtra(TYPE , type);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(TYPE , GRAPHICS);

        switch(type){
            case GRAPHICS :
                displayBasicGraphics();
                break ;
            case TEXTS :
                displayBasicTexts();
                break ;
        }


    }

    /**
     * 基本图形
     */
    private void displayBasicGraphics() {
        setContentView(new BasicGraphics(this));
        setTitle("Basic Graphics");
    }

    private void displayBasicTexts(){
        setContentView(new BasicTexts(this));
        setTitle("Basic Texts");
    }
}
