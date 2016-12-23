package app.hoocchi.perfectdemo.translucentbar_demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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


        setTranslucentBar();
    }

    private void setTranslucentBar() {
        setTheme(R.style.LolipopDrawerTheme);
        setContentView(R.layout.activity_translucent_bar_on_kitkat_drawer);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setFitsSystemWindows(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setFitsSystemWindows(true);
    }
}
