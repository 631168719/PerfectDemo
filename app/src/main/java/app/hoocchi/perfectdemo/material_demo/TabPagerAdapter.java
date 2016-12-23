package app.hoocchi.perfectdemo.material_demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import app.hoocchi.perfectdemo.DataCenter;

/**
 * Created by st on 2016/11/18.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabPagerAdapter(FragmentManager fm , List<Fragment> fragments){
        this(fm);
        mFragments = fragments ;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DataCenter.mTabTitles[position% DataCenter.mTabTitles.length];
    }
}
