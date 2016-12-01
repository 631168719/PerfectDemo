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
    }

    public void showPreferenceFromXML(View v){
        Intent i = new Intent(this , PreferenceActivity1.class);
        i.putExtra(PreferenceActivity1.TYPE , PreferenceActivity1.TYPE_XML);
        startActivity(i);
    }

    public void showPreferenceFromCode(View v){
        Intent i = new Intent(this , PreferenceActivity1.class);
        i.putExtra(PreferenceActivity1.TYPE , PreferenceActivity1.TYPE_CODE);
        startActivity(i);
    }
}
