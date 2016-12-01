package app.hoocchi.perfectdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.hoocchi.perfectdemo.material_demo.MaterialMainActivity;
import app.hoocchi.perfectdemo.preference_demo.PreferenceMainActivity;
import app.hoocchi.perfectdemo.transition_demo.TransitionMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void goActivity(Class cls){
        Intent i = new Intent(this , cls);
        startActivity(i);
    }

    public void showTransitionDemo(View v){
        goActivity(TransitionMainActivity.class);
    }

    public void showMaterialDemo(View v){
        goActivity(MaterialMainActivity.class);
    }

    public void showPreferenceDemo(View vi){
        goActivity(PreferenceMainActivity.class);
    }
}
