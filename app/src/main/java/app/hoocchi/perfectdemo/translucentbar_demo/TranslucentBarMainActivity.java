package app.hoocchi.perfectdemo.translucentbar_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hoocchi.perfectdemo.R;

public class TranslucentBarMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_bar);
        getSupportActionBar().setTitle("StatusBar Demo");
    }

    public void showTranslucentBarOnKitKat(View v){
        startActivity(new Intent(this , TranslucentBarOnKitKat.class));
    }

    public void showTranslucentBarOnLolipop(View v){
        startActivity(new Intent(this , TranslucentBarOnLolipop.class));
    }

    public void showTranslucentBarOnKitKatDrawer(View v){
        startActivity(new Intent(this , TranslucentBarOnKitKatDrawer.class));
    }

    public void showTranslucentBarOnLolipopDrawer(View v) {
        startActivity(new Intent(this , TranslucentBarOnLolipopDrawer.class));
    }


}
