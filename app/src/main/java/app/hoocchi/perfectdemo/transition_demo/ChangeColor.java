package app.hoocchi.perfectdemo.transition_demo;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by st on 2016/11/17.
 */
public class ChangeColor extends Transition {

    private static final String PROPNAME_BACKGROUND_COLOR = "app.hoocchi.perfectdemo:changeColor:backgroundColor";
    private static final String PROPNAME_TEXT_COLOR = "app.hoocchi.perfectdemo:changeColor:textColor";

    private static final String[] mTransitionProperties = {
            PROPNAME_BACKGROUND_COLOR,
            PROPNAME_TEXT_COLOR
    };

    public ChangeColor() {

    }

    public ChangeColor(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;

        if (view instanceof TextView) {
            transitionValues.values.put(PROPNAME_TEXT_COLOR, ((TextView) view).getCurrentTextColor());
        } else {
            transitionValues.values.put(PROPNAME_BACKGROUND_COLOR, view.getBackground());
        }

    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        final View view = endValues.view;

        ValueAnimator animator = null;

        if (view instanceof TextView) {
            int startColor = (int) startValues.values.get(PROPNAME_TEXT_COLOR);
            int endColor = (int) endValues.values.get(PROPNAME_TEXT_COLOR);

            if (startColor != endColor) {
                animator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object value = animation.getAnimatedValue();
                        if (value != null) {
                            ((TextView) view).setTextColor((Integer) value);
                        }
                    }
                });
            }

        } else {
            Drawable startDrawable = (Drawable) startValues.values.get(PROPNAME_BACKGROUND_COLOR);
            Drawable endDrawable = (Drawable) startValues.values.get(PROPNAME_BACKGROUND_COLOR);

            if (startDrawable instanceof ColorDrawable && endDrawable instanceof ColorDrawable) {
                int startColor = ((ColorDrawable) startDrawable).getColor();
                int endColor = ((ColorDrawable) endDrawable).getColor();

                if (startColor != endColor) {
                    animator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, endColor);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Object value = animation.getAnimatedValue();
                            if (value != null) {
                                view.setBackgroundColor((Integer) value);
                            }
                        }
                    });
                }
            }
        }

        return animator;
    }

    @Override
    public String[] getTransitionProperties() {
        return mTransitionProperties;
    }
}
