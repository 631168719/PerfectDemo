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

    public void jump(View v) {
        Intent i = new Intent(this, PreferenceActivityDemo.class);
        switch (v.getId()) {
            case R.id.xml_preference:
                i.putExtra(PreferenceActivityDemo.TYPE, PreferenceActivityDemo.TYPE_XML);
                startActivity(i);
                break;
            case R.id.java_preference:
                i.putExtra(PreferenceActivityDemo.TYPE, PreferenceActivityDemo.TYPE_XML);
                startActivity(i);
                break;
            case R.id.fragment_preference:
                startActivity(new Intent(this, PreferenceFragmentDemo.class));
                break;
        }
    }

}
