package app.hoocchi.perfectdemo.material_demo;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import app.hoocchi.perfectdemo.R;

public class MaterialMainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_main);

        setupDrawerLayout();

        if (savedInstanceState == null) {
            replaceContainer(MaterialDemo1Fragment.newInstance());
        }
    }

    private void setupDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


//        设置MenuItem的文字与图标的状态变化颜色
//        ColorStateList colorStateList = getColorStateList(R.color.theme_red_primary_light ,
//                R.color.theme_red_primary_dark);
//        mNavigationView.setItemTextColor(colorStateList);
//        mNavigationView.setItemIconTintList(colorStateList);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();

                int id = item.getItemId();

                switch (id) {
                    case R.id.demo_item_one:
                        mNavigationView.setCheckedItem(id);
                        replaceContainer(MaterialDemo1Fragment.newInstance());
                        break;
                    case R.id.demo_item_two:
                        mNavigationView.setCheckedItem(id);
                        replaceContainer(MaterialDemo2Fragment.newInstance());
                        break;
                    case R.id.demo_item_three:
                        mNavigationView.setCheckedItem(id);
                        break;
                    case R.id.menu_item_settings:
                        break;
                    case R.id.menu_item_about:
                        break;
                }
                return true;
            }
        });
    }

    private ColorStateList getColorStateList(@ColorRes int normalColor, @ColorRes int checkedColor) {
        int[][] states = {
                new int[]{android.R.attr.state_checked},
                new int[]{}
        };

        int[] colors = {
                ContextCompat.getColor(this, checkedColor),
                ContextCompat.getColor(this, normalColor)
        };

        return new ColorStateList(states, colors);
    }


    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }

        super.onBackPressed();
    }

    private void replaceContainer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, fragment)
                .commit();
    }
}
