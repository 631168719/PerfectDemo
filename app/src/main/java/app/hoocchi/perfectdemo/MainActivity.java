package app.hoocchi.perfectdemo;

import android.os.Bundle;
import android.view.View;

import app.hoocchi.perfectdemo.custom_view_demo.CustomViewMainActivity;
import app.hoocchi.perfectdemo.material_demo.MaterialMainActivity;
import app.hoocchi.perfectdemo.preference_demo.PreferenceMainActivity;
import app.hoocchi.perfectdemo.recycler_view_demo.RecyclerViewMainActivity;
import app.hoocchi.perfectdemo.transition_demo.TransitionMainActivity;
import app.hoocchi.perfectdemo.translucent_bar_demo.TranslucentBarMainActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View v){
        switch(v.getId()){
            case R.id.show_transition_demo :
                jumpActivity(TransitionMainActivity.class);
                break;
            case R.id.show_material_demo:
                jumpActivity(MaterialMainActivity.class);
                break;
            case R.id.show_preference_demo:
                jumpActivity(PreferenceMainActivity.class);
                break;
            case R.id.show_status_bar_demo:
                jumpActivity(TranslucentBarMainActivity.class);
                break;
            case R.id.show_recycler_view_demo:
                jumpActivity(RecyclerViewMainActivity.class);
                break;
            case R.id.show_custom_view_demo :
                jumpActivity(CustomViewMainActivity.class);
                break;
        }
    }
}
