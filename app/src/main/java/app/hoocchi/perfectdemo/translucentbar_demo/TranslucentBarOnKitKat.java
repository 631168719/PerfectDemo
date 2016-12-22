package app.hoocchi.perfectdemo.translucentbar_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;

import app.hoocchi.perfectdemo.R;

public class TranslucentBarOnKitKat extends AppCompatActivity {

    public static final String TYPE = "type";
    public static final int TYPE_CODE = 0 ;
    public static final int TYPE_STYLE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(TYPE , TYPE_CODE);

        if (type == TYPE_CODE) {
            setTranslucentStatusBarByCode();
        } else if (type == TYPE_STYLE) {
            setTranslucentBarByStyle();
        }
    }


    /**
     * 当前Activity需要实现NoActionBar的Theme
     */
    private void setTranslucentStatusBarByCode(){
        setTheme(R.style.AppTheme_NoActionBar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_translucent_bar_on_kit_kat);
        setTranslucentBar();
    }

    /**
     * 当前Activity需要实现NoActionBar的Theme，且需要设置如下属性：
     *  <item name="android:windowTranslucentStatus">true</item>
     */
    private void setTranslucentBarByStyle(){
        setTheme(R.style.TranslucentOnKitKatTheme);
        setContentView(R.layout.activity_translucent_bar_on_kit_kat);
        setTranslucentBar();
    }

    /**
     * 1. 使用根View实现透明状态栏的方式：
     *  1) Activity使用NoActionBar的Theme或调用requestWindowFeature(Window.FEATURE_NO_TITLE)
     *
     *  2) 首先调用window的addFlag()设置FLAG_TRANSLUCENT_STATUS的属性
     *
     *  3) 执行上述步骤后，根View的内容会显示在状态栏上，有两种解决方式：
     *
     *   ① 给根View设置android:fitSystemWindows="true"属性，，会让根View的子View等内容显示
     *     在状态栏下方，但是状态栏会显示白色（根View的背景色），存在根View背景色与内容背景色不一致问题。
     *     该方式使用于根View与子View的背景色一致或背景是图片时使用。
     *
     *   ② 给根View设置与状态栏等高的空白Empty来占据状态栏的高度，这样内容可以显示在
     *     状态栏下方。（不推荐）
     *
     *   ③ 给内容View设置android:fitSystemWindows="true"属性。
     *
     *   ④ 给内容View设置PaddingTop或空白View来占据状态栏高度。
     *
     */
    private void setTranslucentBar(){
        //使用根View实现透明状态栏方式一：
//        ViewGroup root = (ViewGroup) findViewById(R.id.root);
//        root.setFitsSystemWindows(true);
        //设置paddingTop的效果与setFitSystemWindows的效果一样
//        root.setPadding(0, getStatusBarHeight() , 0 , 0);
//        root.setBackgroundColor(Color.parseColor("#123654"));

        //使用根View实现透明状态栏方式二：
//        在根View与内容View之间设置一个与状态栏等高的EmptyView

        //使用内容View实现透明状态栏方式三：
        ViewGroup contentView = (ViewGroup) findViewById(R.id.content);
        contentView.setFitsSystemWindows(true);

        //使用内容View实现透明状态栏方式四：
//        contentView.setPadding(0,getStatusBarHeight(),0,0);

        //设置EmptyView
//        View emptyView = findViewById(R.id.empty);
//        emptyView.setVisibility(View.VISIBLE);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT ,
//                getStatusBarHeight()
//        );
//        emptyView.setLayoutParams(lp);
    }


    private int getStatusBarHeight(){
        int id = getResources().getIdentifier("status_bar_height" , "dimen" , "android");
        if(id > 0){
            return getResources().getDimensionPixelSize(id);
        }

        return 0;
    }


}
