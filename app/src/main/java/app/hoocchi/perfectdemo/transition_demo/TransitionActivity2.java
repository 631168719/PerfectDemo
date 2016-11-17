package app.hoocchi.perfectdemo.transition_demo;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;

import app.hoocchi.perfectdemo.R;

public class TransitionActivity2 extends TransitionBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);

        setupToolBar("Transitions");
        setupWindowTransition();
    }

    private void setupWindowTransition() {
        int transitionType = getIntent().getIntExtra(EXTRA_TRANSITION_TYPE , EXTRA_TRANSITION_TYPE_EXPLODE);
        int programmingType = getIntent().getIntExtra(EXTRA_PROGRAMMING_TYPE , EXTRA_PROGRAMMING_TYPE_CODE);

        if(transitionType == EXTRA_TRANSITION_TYPE_EXPLODE){
            if(programmingType == EXTRA_PROGRAMMING_TYPE_CODE){
                setExplodeFromCode();
            }else if(programmingType == EXTRA_PROGRAMMING_TYPE_XML){
                setExplodeFromXML();
            }
        }else if(transitionType == EXTRA_TRANSITION_TYPE_SLIDE){
            if(programmingType == EXTRA_PROGRAMMING_TYPE_CODE){
                setSlideFromCode();
            }else if(programmingType == EXTRA_PROGRAMMING_TYPE_XML){
                setSlideFromXML();
            }
        }else if(transitionType == EXTRA_TRANSITION_TYPE_FADE){
            if(programmingType == EXTRA_PROGRAMMING_TYPE_CODE){
                setFadeFromCode();
            }else if(programmingType == EXTRA_PROGRAMMING_TYPE_XML){
                setFadeFromXML();
            }
        }
    }

    private void setFadeFromXML() {
        Fade fade = (Fade) TransitionInflater.from(this)
                .inflateTransition(R.transition.fade);
        getWindow().setEnterTransition(fade);
    }

    private void setFadeFromCode() {
        Fade fade = new Fade(Fade.IN);
        fade.setDuration(300);
        fade.setInterpolator(new FastOutSlowInInterpolator());
        getWindow().setEnterTransition(fade);
    }

    private void setSlideFromXML() {
        Slide slide = (Slide) TransitionInflater.from(this)
                .inflateTransition(R.transition.slide_bottom_transition);
        slide.setSlideEdge(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);
    }

    private void setSlideFromCode() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);
        slide.setDuration(500);
        getWindow().setEnterTransition(slide);
    }

    private void setExplodeFromCode(){
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
    }

    private void setExplodeFromXML(){
        Explode explode = (Explode) TransitionInflater.from(this)
                .inflateTransition(R.transition.explode_transition);
        getWindow().setEnterTransition(explode);
    }
}
