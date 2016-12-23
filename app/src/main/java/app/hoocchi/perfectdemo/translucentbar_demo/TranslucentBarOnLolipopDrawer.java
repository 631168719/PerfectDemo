package app.hoocchi.perfectdemo.translucentbar_demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.hoocchi.perfectdemo.DialogManager;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.VersionManager;

public class TranslucentBarOnLolipopDrawer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(VersionManager.isLowerLolipop()){
            DialogManager.showAlertDialog(this, "请在Android 5.0+ 版本上查看效果噢！",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            return ;
        }

        setContentView(R.layout.activity_translucent_bar_on_lolipop_drawer);
    }



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
}
