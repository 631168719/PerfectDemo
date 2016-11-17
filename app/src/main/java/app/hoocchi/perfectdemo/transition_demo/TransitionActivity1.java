package app.hoocchi.perfectdemo.transition_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import app.hoocchi.perfectdemo.R;

public class TransitionActivity1 extends TransitionBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition1);

        setupToolBar("Transitions");
        setupWindowTransition();
    }

    private void setupWindowTransition() {
        Visibility fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
    }

    public void excuteFadeByCode(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_FADE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_CODE);
        transitionToActivity(i);
    }

    public void excuteFadeByXML(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_FADE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_XML);
        transitionToActivity(i);
    }

    public void excuteExplodeByCode(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_EXPLODE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_CODE);
        transitionToActivity(i);
    }


    public void excuteExplodeByXML(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_EXPLODE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_XML);
        transitionToActivity(i);
    }

    public void excuteSlideByCode(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_SLIDE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_CODE);
        transitionToActivity(i);
    }

    public void excuteSlideByXML(View v){
        Intent i = new Intent(this, TransitionActivity2.class);
        i.putExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_SLIDE);
        i.putExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_XML);
        transitionToActivity(i);
    }

    public void exitWithoutTransition(View v){
        //如果没有设置 ReturnTransition，则默认使用EnterTransition
        finishAfterTransition();
    }

    public void exitWithTransition(View v){
        //给ReturnTransition设置 Slide Bottom动画
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.BOTTOM);
        slide.setDuration(500);
        getWindow().setReturnTransition(slide);

        finishAfterTransition();
    }

    private void transitionToActivity(Intent i){
        Pair[] pairs = TransitionHelper.createSafeTransitionPairs(this , true);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,pairs);
        startActivity(i , options.toBundle());
    }

}
