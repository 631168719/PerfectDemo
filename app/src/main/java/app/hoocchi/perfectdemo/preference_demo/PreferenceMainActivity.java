package app.hoocchi.perfectdemo.preference_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hoocchi.perfectdemo.R;

public class PreferenceMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_main);
        getSupportActionBar().setTitle("Preference Demo");
    }

    public void showPreferenceActivityFromXml(View v){
        Intent i = new Intent(this , PreferenceActivityDemo.class) ;
        i.putExtra(PreferenceActivityDemo.TYPE , PreferenceActivityDemo.TYPE_XML);
        startActivity(i);
    }

    public void showPreferenceActivityFromJava(View v) {
        Intent i = new Intent(this , PreferenceActivityDemo.class) ;
        i.putExtra(PreferenceActivityDemo.TYPE , PreferenceActivityDemo.TYPE_CODE);
        startActivity(i);
    }

    public void showPreferenceFragment(View v){
        startActivity(new Intent(this , PreferenceFragmentDemo.class));
    }

}
