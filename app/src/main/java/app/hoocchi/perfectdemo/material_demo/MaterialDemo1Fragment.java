package app.hoocchi.perfectdemo.material_demo;


import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class MaterialDemo1Fragment extends Fragment {

    private CoordinatorLayout mCoordinatorLayout ;
    private Toolbar mToolBar ;
    private SwipeRefreshLayout mSwipeRefreshLayout ;
    private RecyclerView mRecyclerView ;
    private FloatingActionButton mFab ;


    public MaterialDemo1Fragment() {
        // Required empty public constructor
    }

    public static MaterialDemo1Fragment newInstance(){
        return new MaterialDemo1Fragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_demo1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //给Fragment设置OptionMenu
        setHasOptionsMenu(true);

        initViews(view);
    }

    private void initViews(View view) {
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_layout);
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar_layout);


        //因为Theme是NoActionBar的，所以需要手动设置ActionBar，否则不显示OptionMenu
        ((MaterialMainActivity)getActivity()).setSupportActionBar(mToolBar);
//        代码设置标题和导航图标
//        mToolBar.setTitle("Title");
//        mToolBar.setNavigationIcon(R.drawable.ic_settings_grey_500_24dp);
//        代码设置ToolBar的layout_scrollFlags
//        AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams) mToolBar.getLayoutParams();
//        lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
//        mToolBar.setLayoutParams(lp);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开抽屉
                ((MaterialMainActivity)getActivity()).openDrawer();
            }
        });

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new RecyclerAdapter(DataUtils.mStrArrays));

        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showSnackBar("You Clicked FAB !");
            }
        });
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_option_menu , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_item_list:
                return true ;
            case R.id.menu_item_more :
                showSnackBar("Are You Sure To Enter More UI ?" , "Sure");
                return true ;
            case R.id.menu_item_info :
                showSnackBar("Are You Sure To Enter Info UI ?" , "Sure" , R.color.theme_yellow_accent);
                return true ;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSnackBar(String msg){
        Snackbar.make(mCoordinatorLayout , msg , Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackBar(String msg , String actionText){
       Snackbar.make(mCoordinatorLayout , msg , Snackbar.LENGTH_LONG)
               .setAction(actionText, new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               })
               .show();
    }

    private void showSnackBar(String msg , String actionText , @ColorRes int actionTextColor){
        Snackbar.make(mCoordinatorLayout , msg , Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(getActivity() , actionTextColor))
                .setAction(actionText, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                    }
                })
                .show();
    }
}
