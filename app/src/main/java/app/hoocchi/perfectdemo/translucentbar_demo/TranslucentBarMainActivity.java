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

    public void showTranslucentBarOnKitKatByCode(View v){
        Intent intent = new Intent(this , TranslucentBarOnKitKat.class);
        intent.putExtra(TranslucentBarOnKitKat.TYPE , TranslucentBarOnKitKat.TYPE_CODE);
        startActivity(intent);
    }

    public void showTranslucentBarOnKitKatByStyle(View v) {
        Intent intent = new Intent(this, TranslucentBarOnKitKat.class);
        intent.putExtra(TranslucentBarOnKitKat.TYPE, TranslucentBarOnKitKat.TYPE_STYLE);
        startActivity(intent);
    }

    public void showTranslucentBarOnLolipopByCode(View v){

    }

    public void showTranslucentBarOnLolipopByStyle(View v) {

    }

    public void showTranslucentBarOnKitKatDrawer(View v){
        startActivity(new Intent(this , TranslucentBarOnDrawerLayout.class));
    }

    public void showTranslucentBarOnLolipopDrawer(View v) {

    }





}
