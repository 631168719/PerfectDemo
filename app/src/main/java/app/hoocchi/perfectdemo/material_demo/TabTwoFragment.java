package app.hoocchi.perfectdemo.material_demo;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.VersionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTwoFragment extends Fragment {

    private TextInputLayout mInputLayout1;
    private TextInputLayout mInputLayout2;
    private TextInputLayout mInputLayout3;

    private FloatingActionButton mFab;

    private CardView mCardView;
    private LinearLayout mInputLayout;
    private TextView mLoginText;

    public TabTwoFragment() {
        // Required empty public constructor
    }

    public static TabTwoFragment newInstance() {
        return new TabTwoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInputLayout1 = (TextInputLayout) view.findViewById(R.id.text_input_layout1);
        mInputLayout2 = (TextInputLayout) view.findViewById(R.id.text_input_layout2);
        mInputLayout3 = (TextInputLayout) view.findViewById(R.id.text_input_layout3);

        mInputLayout2.setErrorEnabled(true);
        mInputLayout2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > mInputLayout2.getCounterMaxLength()) {
                    mInputLayout2.setError("您输入的密码长度不对!");
                } else {
                    mInputLayout2.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mInputLayout3.setErrorEnabled(true);
        mInputLayout3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > mInputLayout3.getCounterMaxLength()) {
                    mInputLayout3.setError("您输入的邮箱长度不对!");
                } else {
                    mInputLayout3.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    login();
                } else {
                    Snackbar.make(mCardView, "请输入对应的信息", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        mCardView = (CardView) view.findViewById(R.id.card_view);
        mLoginText = (TextView) view.findViewById(R.id.login_result);
        mInputLayout = (LinearLayout) view.findViewById(R.id.input_layout);

        mLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLoginUI();
            }
        });

    }

    private void backLoginUI() {
        if (VersionManager.isHigherLolipop()) {
            animateAfterLolipop(true);
        } else {
            animatePreLolipop(true);
        }
    }

    private void login() {
        if (VersionManager.isHigherLolipop()) {
            animateAfterLolipop(false);
        } else {
            animatePreLolipop(false);
        }
    }

    private void animatePreLolipop(boolean isBackLogin) {
        if(isBackLogin){
           mLoginText.animate()
                   .alpha(0f)
                   .scaleY(0f)
                   .scaleY(0f)
                   .setDuration(300)
                   .setListener(new AnimatorListenerAdapter() {
                       @Override
                       public void onAnimationEnd(Animator animation) {
                           mFab.animate()
                                   .alpha(1f)
                                   .scaleX(1f)
                                   .scaleY(1f)
                                   .setListener(null)
                                   .setInterpolator(new AccelerateInterpolator())
                                   .start();

                           mCardView.setCardBackgroundColor(Color.WHITE);
                           mInputLayout.setVisibility(View.VISIBLE);
                           mLoginText.setVisibility(View.INVISIBLE);
                           mLoginText.setAlpha(1f);
                           mLoginText.setScaleX(1f);
                           mLoginText.setScaleY(1f);
                       }
                   })
                   .start();
        }else{
           mFab.animate()
                   .alpha(0f)
                   .scaleX(0f)
                   .scaleY(0f)
                   .setStartDelay(100)
                   .setListener(new AnimatorListenerAdapter() {
                       @Override
                       public void onAnimationEnd(Animator animation) {
                           mCardView.setCardBackgroundColor(ContextCompat.getColor(
                                   getActivity() , R.color.colorAccent
                           ));

                           mInputLayout.setVisibility(View.INVISIBLE);
                           mLoginText.setVisibility(View.VISIBLE);
                       }
                   })
                   .start();
        }
    }

    private void animateAfterLolipop(boolean isBackLogin) {
        int cx = 0;
        int cy = 0;
        float startRadius = 0;
        float endRadius = 0;

        //是否返回登录界面
        if (isBackLogin) {
            cx = (mLoginText.getLeft() + mLoginText.getRight()) / 2;
            cy = (mLoginText.getTop() + mLoginText.getBottom()) / 2;
            startRadius = (float) Math.hypot(mCardView.getWidth(), mCardView.getHeight());
        } else {
            cx = (mFab.getLeft() + mFab.getRight()) / 2;
            cy = (mFab.getTop() + mFab.getBottom()) / 2;
            endRadius = (float) Math.hypot(mCardView.getWidth(), mCardView.getHeight());
        }

        Animator animator = ViewAnimationUtils.createCircularReveal(mCardView, cx, cy, startRadius, endRadius);

        if (isBackLogin) {
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mCardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_light));
                    mFab.setVisibility(View.VISIBLE);
                    mInputLayout.setVisibility(View.VISIBLE);
                    mLoginText.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            mCardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            mFab.setVisibility(View.INVISIBLE);
            mInputLayout.setVisibility(View.INVISIBLE);
            mLoginText.setVisibility(View.VISIBLE);
        }
        animator.start();
    }

    private boolean checkInput() {
        boolean isSuccesss = true;

        String input1 = mInputLayout1.getEditText().getText().toString();
        String input2 = mInputLayout2.getEditText().getText().toString();
        String input3 = mInputLayout3.getEditText().getText().toString();

        if (TextUtils.isEmpty(input1)) {
            isSuccesss = false;
        }

        if (TextUtils.isEmpty(input2) || input2.length() > mInputLayout2.getCounterMaxLength()) {
            isSuccesss = false;
        }

        if (TextUtils.isEmpty(input3) || input3.length() > mInputLayout3.getCounterMaxLength()) {
            isSuccesss = false;
        }

        return isSuccesss;
    }


}
