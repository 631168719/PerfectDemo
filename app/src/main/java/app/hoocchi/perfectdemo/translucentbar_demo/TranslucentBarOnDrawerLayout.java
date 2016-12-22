package app.hoocchi.perfectdemo.translucentbar_demo;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import app.hoocchi.perfectdemo.R;

public class TranslucentBarOnDrawerLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_translucent_bar_on_drawerlayout);

        /**
         * 实现DrawerLayout的内容延伸至StatusBar的方法 : (5.0+)
         *
         * 1. 给布局中的DrawerLayout以及NavigationView添加android:fitSystemWindows="true"
         * 2. 给当前Activity设置如下主题 :
         *
         *  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
         *    <item name="colorPrimary">@color/colorPrimary</item>
         *    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
         *    <item name="colorAccent">@color/colorAccent</item>
         *
         *    <item name="android:windowDrawsSystemBarBackgrounds">true</item>
         *    <item name="android:statusBarColor">@android:color/transparent</item>
         *  </style>
         *
         */

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


}
