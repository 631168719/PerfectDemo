package app.hoocchi.perfectdemo.transition_demo;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import app.hoocchi.perfectdemo.R;

public class ShareElementActivity extends TransitionBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);

        setupToolBar("");
        setupWindowTransition();
        setupLayout();
    }

    private void setupWindowTransition() {
        //ToolBar Title的共享效果，只改变动画时间
        getWindow().getEnterTransition().setDuration(500);
    }

    private void setupLayout(){
        //为fragment1设置Exit 和 Reenter动画
        ShareElementFragment1 fragment1 = ShareElementFragment1.newInstance();
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(500);
        fragment1.setExitTransition(slide);
        fragment1.setReenterTransition(slide);

        //为MainActivity 与 Fragment1之间共享元素设置动画
        fragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.share_element_content , fragment1)
                .commit();
    }
}
