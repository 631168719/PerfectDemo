package app.hoocchi.perfectdemo.custom_view_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hoocchi.perfectdemo.R;

public class CustomViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_main);
        setTitle("CustomView Demo");
    }

    public void jump(View v){
        switch (v.getId()){
            case R.id.basic_graphics :
                DisplayViewActivity.jump(this , DisplayViewActivity.GRAPHICS);
                break;
            case R.id.basic_texts :
                DisplayViewActivity.jump(this , DisplayViewActivity.TEXTS);
                break;
        }
    }
}
