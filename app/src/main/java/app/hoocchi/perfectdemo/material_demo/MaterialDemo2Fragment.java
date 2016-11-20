package app.hoocchi.perfectdemo.material_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialDemo2Fragment extends Fragment {

    private Toolbar mToolBar ;
    private TabLayout mTabLayout ;
    private ViewPager mViewPager ;

    public MaterialDemo2Fragment() {
        // Required empty public constructor
    }

    public static MaterialDemo2Fragment newInstance(){
        return new MaterialDemo2Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_demo2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        initViews(view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_option_menu , menu);
    }

    private void initViews(View view) {
        mToolBar = (Toolbar) view.findViewById(R.id.tool_bar);

        ((MaterialMainActivity)getActivity()).setSupportActionBar(mToolBar);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MaterialMainActivity)getActivity()).openDrawer();
            }
        });

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(TabOneFragment.newInstance());
        fragments.add(TabTwoFragment.newInstance());
        fragments.add(TabThreeFragment.newInstance());
        mViewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager() , fragments));

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
//        设置Tab可滑动
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        当Mode为Fixed时，该属性才生效，设置Tab居中
//        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }
}
