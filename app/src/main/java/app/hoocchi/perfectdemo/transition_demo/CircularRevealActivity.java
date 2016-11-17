package app.hoocchi.perfectdemo.transition_demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import app.hoocchi.perfectdemo.R;

public class CircularRevealActivity extends TransitionBaseActivity implements View.OnClickListener{


    private Toolbar mToolBar ;

    private List<View> mBtnViews = new ArrayList<>();

    private ViewGroup mRevealRoot ;

    private Interpolator mInterpolator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        mToolBar = setupToolBar("Circular Reveal");

        setupLayout();

        setupWindowTransition();
    }

    private void setupLayout() {

        mRevealRoot = (ViewGroup) findViewById(R.id.reveal_root);

        ImageView greenBall= (ImageView) findViewById(R.id.ball_green);
        greenBall.setOnClickListener(this);

        ImageView redBall= (ImageView) findViewById(R.id.ball_red);
        redBall.setOnClickListener(this);

        ImageView blueBall= (ImageView) findViewById(R.id.ball_blue);
        blueBall.setOnClickListener(this);

        ImageView yellowBall= (ImageView) findViewById(R.id.ball_yellow);
        yellowBall.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    int cx = (int) motionEvent.getRawX();
                    int cy = (int) motionEvent.getRawY();
                    animateCircularRevealColor(mRevealRoot , R.color.ball_yellow , cx , cy );
                }
                return true;
            }
        });

        mBtnViews.add(greenBall);
        mBtnViews.add(redBall);
        mBtnViews.add(blueBall);
        mBtnViews.add(yellowBall);
    }

    private void setupWindowTransition() {
        mInterpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion);

        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                hideShareElements();
                animateCircularReveal(mToolBar);
                animateBallShow(true);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        final Fade fade = new Fade();
        fade.setDuration(500);
        fade.setStartDelay(200);
        getWindow().setReturnTransition(fade);

        fade.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                transition.removeListener(this);
                animateBallShow(false);
                animateCircularRevealHide(mRevealRoot);
            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void animateCircularRevealHide(View view) {
        int centerX = (view.getLeft() + view.getRight())/2;
        int centerY = (view.getTop() + view.getBottom())/2;

        int startRadius = Math.max(view.getWidth() , view.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(view , centerX , centerY , startRadius , 0);
        animator.setDuration(300);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mRevealRoot.setVisibility(View.GONE);
            }
        });
    }

    private void animateBallShow(boolean isShow) {
        for(int i =0 ; i<mBtnViews.size() ; i++){
            View child = mBtnViews.get(i);
            if(isShow){
                child.animate()
                        .setStartDelay(100+ i*100)
                        .setInterpolator(mInterpolator)
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f);
            }else{
                child.animate()
                        .setStartDelay(i)
                        .setInterpolator(mInterpolator)
                        .alpha(0f)
                        .scaleX(0f)
                        .scaleY(0f);
            }

        }
    }

    private void animateCircularReveal(View view) {
        int centerX = (view.getLeft() + view.getRight())/2;
        int centerY = (view.getTop() + view.getBottom())/2;

        int finalRadius = Math.max(view.getWidth() , view.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(view , centerX , centerY , 0 , finalRadius);
        view.setVisibility(View.VISIBLE);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void hideShareElements() {
        findViewById(R.id.title_ball).setVisibility(View.GONE);
    }

    @Override
    public void onClick(final View view) {

        switch(view.getId()){
            case R.id.ball_green :
                animateCircularRevealColor(mRevealRoot , R.color.ball_green);
                break;
            case R.id.ball_red :
                final ViewGroup.LayoutParams originLp = view.getLayoutParams();
                Transition transition = TransitionInflater.from(this)
                        .inflateTransition(R.transition.changebounds_with_arcmotion);
                transition.addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        transition.removeListener(this);
                        animateCircularRevealColor(mRevealRoot , R.color.ball_red);
                        view.setLayoutParams(originLp);
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
                TransitionManager.beginDelayedTransition(mRevealRoot , transition);

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                        , RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                view.setLayoutParams(lp);
                break;
            case R.id.ball_blue :
                animateBallShow(false);
                int cx = mRevealRoot.getWidth() /2 ;
                int cy = 0 ;
                Animator animator = animateCircularRevealColor(mRevealRoot , R.color.ball_blue , cx ,cy );
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animateBallShow(true);
                    }
                });
                break;
        }
    }

    private void animateCircularRevealColor(View view ,@ColorRes int color){
        int centerX = (view.getLeft() + view.getRight())/2;
        int centerY = (view.getTop() + view.getBottom())/2;

        float endRadius = (float) Math.hypot(view.getWidth() , view.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(view , centerX , centerY , 0 ,endRadius);

        view.setBackgroundColor(ContextCompat.getColor(this , color));

        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private Animator animateCircularRevealColor(View view , @ColorRes int color , int cx , int cy){
        float endRadius = (float) Math.hypot(view.getWidth() , view.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(view , cx ,cy , 0 , endRadius);
        view.setBackgroundColor(ContextCompat.getColor(this , color));
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

        return animator;
    }
}
