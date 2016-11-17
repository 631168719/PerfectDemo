package app.hoocchi.perfectdemo.transition_demo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.hoocchi.perfectdemo.R;

public class CustomTransitionActivity extends TransitionBaseActivity {

    private LinearLayout mRoot;
    private Button mBtn;
    private View mView;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);

        setupToolBar("Custom Transitions");
        setupLayout();
        getWindow().setEnterTransition(new Explode().setDuration(500));
    }

    private void setupLayout() {
        mRoot = (LinearLayout) findViewById(R.id.root);
        mBtn = (Button) findViewById(R.id.button);
        mView = findViewById(R.id.view);
        mText = (TextView) findViewById(R.id.textview);
    }

    public void startCustomTransition(View v) {

        ChangeColor changeColor = new ChangeColor();
        changeColor.setDuration(500);
        TransitionManager.beginDelayedTransition(mRoot, new ChangeColor());

        int textColor = ContextCompat.getColor(this , R.color.theme_purple_accent);
        int backgroundColor = ContextCompat.getColor(this , R.color.light_grey);

        mBtn.setTextColor(textColor);
        mText.setTextColor(textColor);
        mBtn.setBackground(new ColorDrawable(backgroundColor));
        mView.setBackground(new ColorDrawable(backgroundColor));
        mText.setBackground(new ColorDrawable(backgroundColor));

        mText.setScaleY(0.5f);
        mText.setScaleX(0.5f);
    }

}
