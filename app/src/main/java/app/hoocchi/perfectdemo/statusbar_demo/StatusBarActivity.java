package app.hoocchi.perfectdemo.statusbar_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hoocchi.perfectdemo.R;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        getSupportActionBar().setTitle("StatusBar Demo");
    }

    public void showDrawerToStatusBar(View v){
        startActivity(new Intent(this , DrawerLayoutActivity.class));
    }

    public void showTranslucentStatusBar(View v) {
        startActivity(new Intent(this, StatusBarTranslucentActivity.class));
    }


}
