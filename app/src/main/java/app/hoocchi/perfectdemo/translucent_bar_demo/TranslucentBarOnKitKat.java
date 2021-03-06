package app.hoocchi.perfectdemo.translucent_bar_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import app.hoocchi.perfectdemo.DialogManager;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.VersionManager;

public class TranslucentBarOnKitKat extends AppCompatActivity {

    private static final String TYPE = "type";
    private static final int TYPE_CODE = 0 ;
    private static final int TYPE_STYLE = 1 ;

    private static final String METHOD = "method";


    private int mType ;
    private int methodIndex ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(VersionManager.isLowerKitKat()){
            DialogManager.showAlertDialog(this, "请在Android 4.4+ 版本上查看效果噢！",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            return ;
        }

        mType = getIntent().getIntExtra(TYPE , TYPE_CODE);
        methodIndex = getIntent().getIntExtra(METHOD , 1);

        if (mType == TYPE_CODE) {
            setTranslucentBarByCode();
        } else if (mType == TYPE_STYLE) {
            setTranslucentBarByStyle();
        }


        //初始化ToolBar
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
    }


    /**
     * 当前Activity需要实现NoActionBar的Theme
     */
    private void setTranslucentBarByCode(){
        setTheme(R.style.AppTheme_NoActionBar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_translucent_bar_content);
        setTranslucentBar();
    }

    /**
     * 当前Activity需要实现NoActionBar的Theme，且需要设置如下属性：
     *  <item name="android:windowTranslucentStatus">true</item>
     */
    private void setTranslucentBarByStyle(){
        setTheme(R.style.TranslucentOnKitKatTheme);
        setContentView(R.layout.activity_translucent_bar_content);
        setTranslucentBar();
    }

    private void setTranslucentBar(){
        switch(methodIndex){
            case 1 :
                useMethodOne();
                break;
            case 2 :
                useMethodTwo();
                break;
            case 3 :
                useMethodThree();
                break;
            case 4 :
                useMethodFour();
                break;
            case 5 :
                useMethodFive();
                break;
        }
    }

    /**
     * 使用根View实现透明状态栏方式一 ：
     *  1）给根View设置setFitsSystemWindows(true)或android:fitsSystemWindows="true"属性
     *     或者给根View设置PaddingTop，值等于状态栏高度
     *  2）让根View与内容View的背景色保持一致
     *  3）给覆盖View设置背景色
     *
     *  注意：根View包含两个子View ： 一个是内容View、一个是内容View下方的覆盖View
     *
     *  实现原理：设置fitsSystemWindows属性后，子View的内容会显示在状态栏的下面，
     *           如果根View没有设置背景色，则状态栏默认显示白色 ，因此需要让根View
     *           与内容View的背景色一致，根View设置背景色后会导致整个布局的背景色改变，
     *           所以在不需要改变背景色的其他部分上面覆盖一个布局，并设置白色（想要的颜色）
     */
    private void useMethodOne(){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.root_view);
        rootView.setFitsSystemWindows(true);
        //与上面效果一样
//        rootView.setPadding(0 , getStatusBarHeight() , 0 , 0);
        rootView.setBackgroundColor(Color.parseColor("#123654"));

        ViewGroup overlayView = (ViewGroup) findViewById(R.id.overlay_view);
        overlayView.setVisibility(View.VISIBLE);
        overlayView.setBackgroundColor(Color.WHITE);
    }

    /**
     * 使用根View实现透明状态栏方式二：
     *  1）在根View与内容View之间放置一个与状态栏同高的空白View
     *  2）让内容View显示在空白View之下，并且保持空白View与内容View的背景色一致
     */
    private void useMethodTwo(){
        View rootEmptyView = findViewById(R.id.root_empty_view);
        rootEmptyView.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT ,
                getStatusBarHeight()
        );
        rootEmptyView.setBackgroundColor(Color.parseColor("#123654"));
        rootEmptyView.setLayoutParams(lp);
    }

    /**
     * 使用内容View实现透明状态栏方式三：
     *  1）给内容View设置setFitsSystemWindows(true)或android:fitsSystemWindows="true"属性
     *     或者给根View设置PaddingTop，值等于状态栏高度
     */
    private void useMethodThree(){
        ViewGroup contentView = (ViewGroup) findViewById(R.id.content_view);
        contentView.setFitsSystemWindows(true);
        //与上面效果一样
//        contentView.setPadding(0,getStatusBarHeight() , 0 ,0);
    }

    /**
     * 使用内容View实现透明状态栏方式四：
     *  1）在内容View的顶部添加一个与状态栏等高的空白View
     *  2）空白View的背景色与内容View一致
     */
    private void useMethodFour(){
        View contentEmptyView =  findViewById(R.id.content_empty_view);
        contentEmptyView.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT ,
                getStatusBarHeight()
        );
        contentEmptyView.setLayoutParams(lp);
    }

    /**
     * 同时对根View与内容View处理实现透明状态栏方式五：
     *  1）让根View与内容View的背景色保持一致
     *  2）给内容View设置与状态栏等高的MaginTop
     *  3）给覆盖View设置白色背景色
     *
     *  注意：根View包含两个子View：一个是内容View，一个是处于内容View下方的覆盖View
     */
    private void useMethodFive(){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.root_view);
        rootView.setBackgroundColor(Color.parseColor("#123654"));

        ViewGroup contentView = (ViewGroup) findViewById(R.id.content_view);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentView.getLayoutParams();
        lp.topMargin = getStatusBarHeight();
        contentView.setLayoutParams(lp);

        ViewGroup overlayView = (ViewGroup) findViewById(R.id.overlay_view);
        overlayView.setVisibility(View.VISIBLE);
        overlayView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.translucent_bar_menu, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_type_code :
                mType = TYPE_CODE;
                break;
            case R.id.action_type_style :
                mType = TYPE_STYLE;
                break;
            case R.id.action_method_one :
                methodIndex = 1 ;
                break;
            case R.id.action_method_two:
                methodIndex = 2 ;
                break;
            case R.id.action_method_three:
                methodIndex = 3 ;
                break;
            case R.id.action_method_four:
                methodIndex = 4 ;
                break;
            case R.id.action_method_five:
                methodIndex = 5;
                break;
        }

        Intent intent = new Intent(this , TranslucentBarOnKitKat.class);
        intent.putExtra(TYPE , mType);
        intent.putExtra(METHOD , methodIndex);
        startActivity(intent);
        finish();
        overridePendingTransition(0,0);

        return true ;
    }

    private int getStatusBarHeight(){
        int id = getResources().getIdentifier("status_bar_height" , "dimen" , "android");
        if(id > 0){
            return getResources().getDimensionPixelSize(id);
        }

        return 0;
    }
}
