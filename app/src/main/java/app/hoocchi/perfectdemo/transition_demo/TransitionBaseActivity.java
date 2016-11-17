package app.hoocchi.perfectdemo.transition_demo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.hoocchi.perfectdemo.R;

/**
 * Created by st on 2016/11/4.
 */
public class TransitionBaseActivity extends AppCompatActivity{

    public static final String SHARE_ELEMENT_ICON = "share_element_icon";
    public static final String SHARE_ELEMENT_TITLE = "share_element_title";

    public static final String EXTRA_TRANSITION_TYPE = "transition_type";
    public static final int EXTRA_TRANSITION_TYPE_FADE = 0;
    public static final int EXTRA_TRANSITION_TYPE_EXPLODE = 1;
    public static final int EXTRA_TRANSITION_TYPE_SLIDE = 2;

    public static final String EXTRA_PROGRAMMING_TYPE = "type";
    public static final int EXTRA_PROGRAMMING_TYPE_CODE = 0;
    public static final int EXTRA_PROGRAMMING_TYPE_XML = 1;

    protected Toolbar setupToolBar(String title){
        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        ab.setDisplayHomeAsUpEnabled(true);

        return toolBar ;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true ;
    }
}
