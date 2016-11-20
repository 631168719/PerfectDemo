package app.hoocchi.perfectdemo.material_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import app.hoocchi.perfectdemo.DataUtils;
import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialDemo3Fragment extends Fragment {

    private CollapsingToolbarLayout mCollapsingToolbarLayout ;
    private Toolbar mToolBar ;

    private SwipeRefreshLayout mSwipeRefreshLayout ;
    private RecyclerView mRecyclerView ;

    public MaterialDemo3Fragment() {
        // Required empty public constructor
    }

    public static MaterialDemo3Fragment newInstance(){
        return new MaterialDemo3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_demo3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        initViews(view);
    }

    private void initViews(View view) {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout);
        //设置扩张和收缩时的Title颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getActivity(),
//                R.color.colorPrimary));
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getActivity(),
//                R.color.colorAccent));

        mToolBar = (Toolbar) view.findViewById(R.id.tool_bar);
        ((MaterialMainActivity)getActivity()).setSupportActionBar(mToolBar);
        ActionBar ab = ((MaterialMainActivity)getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);

        //两种方式实现点击Menu事件
//        mToolBar.setNavigationIcon(R.drawable.ic_menu);
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.theme_blue_accent , R.color.theme_green_accent ,
                R.color.theme_purple_accent , R.color.theme_red_accent ,
                R.color.theme_yellow_accent
        );
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                } , 4000);
            }
        });


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new RecyclerAdapter(DataUtils.mStrArrays));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_option_menu , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home :
                ((MaterialMainActivity)getActivity()).openDrawer();
                return true ;
        }

        return super.onOptionsItemSelected(item);
    }
}
