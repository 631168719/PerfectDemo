package app.hoocchi.perfectdemo.transition_demo;

import android.os.Bundle;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import app.hoocchi.perfectdemo.R;

public class SceneAnimationActivity extends TransitionBaseActivity implements RadioGroup.OnCheckedChangeListener{

    private FrameLayout mSceneRoot ;

    private Scene mScene1 ;
    private Scene mScene2 ;
    private Scene mScene3 ;

    private ImageView mBall ;

    private TransitionManager mCustomTransitionManager ;

    private int mWidth ;
    private int mHeight ;

    private boolean isSizeChanged = false ;
    private boolean isPositionChanged = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_animation);

        setupToolBar("Scene Animations");

        setupLayout();

        setupWindowTransition();
    }

    private void setupLayout() {
        mSceneRoot = (FrameLayout) findViewById(R.id.scene_root);

        final RadioGroup sceneGroup = (RadioGroup) findViewById(R.id.scene_group);
        RadioGroup animGroup = (RadioGroup) findViewById(R.id.anim_group);

        sceneGroup.setOnCheckedChangeListener(this);
        animGroup.setOnCheckedChangeListener(this);

        //每个Scene对应的Layout布局的根View必须设置ID，否则动画效果有问题
//        mScene1 = new Scene(mSceneRoot , mSceneRoot.findViewById(R.id.scene_layout));
        mScene1 = Scene.getSceneForLayout(mSceneRoot , R.layout.scene_1 , this);
        mScene2 = Scene.getSceneForLayout(mSceneRoot , R.layout.scene_2 , this);
        mScene3 = Scene.getSceneForLayout(mSceneRoot , R.layout.scene_3 , this);

        mScene1.setEnterAction(new Runnable() {
            @Override
            public void run() {
               animateBallShow(true);
            }
        });

        mScene1.setExitAction(new Runnable() {
            @Override
            public void run() {
                animateBallShow(false);
            }
        });

        mCustomTransitionManager = TransitionInflater.from(this)
                .inflateTransitionManager(R.transition.scene3_transition_manager , mSceneRoot);

    }

    private void animateBallShow(boolean show){
        int childCount = mScene1.getSceneRoot().getChildCount();
        for(int i=0 ; i<childCount ; i++){
            View child = mScene1.getSceneRoot().getChildAt(i);
            if(show){
                child.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setStartDelay(100*i)
                        .start();
            }else{
                child.animate()
                        .alpha(0f)
                        .scaleX(0f)
                        .scaleY(0f)
                        .setStartDelay(i)
                        .start();
            }
        }
    }

    private void setupWindowTransition() {
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);
        fade.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                TransitionManager.go(mScene1);
                mBall = (ImageView) mScene1.getSceneRoot().findViewById(R.id.ball_green);
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

    private void changeSize(){
        TransitionManager.beginDelayedTransition(mSceneRoot);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mBall.getLayoutParams();

        if(isSizeChanged){
            lp.width = mWidth ;
            lp.height = mHeight ;
        }else{
            mWidth = lp.width ;
            mHeight = lp.height ;

            lp.width = 200 ;
            lp.height = 200 ;
        }

        isSizeChanged = !isSizeChanged ;
        mBall.setLayoutParams(lp);
    }

    private void changePosition(){
        ChangeBounds changeBounds = new ChangeBounds();
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(90f);
        arcMotion.setMinimumVerticalAngle(0f);
        arcMotion.setMaximumAngle(45f);
        changeBounds.setPathMotion(arcMotion);

        TransitionManager.beginDelayedTransition(mSceneRoot , changeBounds);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mBall.getLayoutParams();

        if(isPositionChanged){
            lp.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        }else{
            lp.removeRule(RelativeLayout.CENTER_IN_PARENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }

        isPositionChanged = !isPositionChanged;
        mBall.setLayoutParams(lp);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch(checkId){
            case R.id.rb1 :
                TransitionManager.go(mScene1);
                mBall = (ImageView) mScene1.getSceneRoot().findViewById(R.id.ball_green);
                break;
            case R.id.rb2 :
                TransitionManager.go(mScene2);
                mBall = (ImageView) mScene1.getSceneRoot().findViewById(R.id.ball_red);
                break;
            case R.id.rb3 :
//                TransitionManager.go(mScene3);
                //使用自定义的TransitionManager
                mCustomTransitionManager.transitionTo(mScene3);
                mBall = (ImageView) mScene1.getSceneRoot().findViewById(R.id.ball_yellow);
                break;
            case R.id.anim1 :
                changeSize();
                break;
            case R.id.anim2 :
                changePosition();
                break;
        }
    }
}
