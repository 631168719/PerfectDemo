package app.hoocchi.perfectdemo.recycler_view_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

import app.hoocchi.perfectdemo.DataCenter;
import app.hoocchi.perfectdemo.DeviceManager;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.recycler_view_demo.adapter.CommonAdapter;
import app.hoocchi.perfectdemo.recycler_view_demo.decoration.DefaultItemDecoration;

public class LayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView ;
    private RecyclerView.LayoutManager mLayoutManager;
    private DefaultItemDecoration mItemDecoration ;

    private boolean isReverse ;
    private int mOrientation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initToolBar();

        initRecyclerView();

    }

    private void initToolBar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
        toolBar.setTitle("LayoutManager");
        setSupportActionBar(toolBar);
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mOrientation = OrientationHelper.VERTICAL;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItemDecoration = new DefaultItemDecoration(this , mOrientation);
        mRecyclerView.addItemDecoration(mItemDecoration);

        CommonAdapter<String> adapter = new CommonAdapter<String>(Arrays.asList(DataCenter.mStrArrays)) {

            @Override
            protected int getLayoutId(int viewType) {
                return R.layout.text_recycler_item;
            }

            @Override
            protected void convert(ViewHolder holder, String data, int position) {
                setItemLayoutParams(holder.itemView);

                TextView mText = holder.getChildView(R.id.item_text);
                mText.setText(data);
            }

        };

        mRecyclerView.setAdapter(adapter);

    }

    private void setItemLayoutParams(View itemView){
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();

        if(mLayoutManager instanceof LinearLayoutManager){

            if(mOrientation == OrientationHelper.VERTICAL){
                lp.width = RecyclerView.LayoutParams.MATCH_PARENT;
                lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }else if(mOrientation == OrientationHelper.HORIZONTAL){
                lp.width = RecyclerView.LayoutParams.WRAP_CONTENT;
                lp.height = RecyclerView.LayoutParams.MATCH_PARENT;
            }

        }else if(mLayoutManager instanceof GridLayoutManager){

            if(mOrientation == OrientationHelper.VERTICAL){
                int screenWidth = DeviceManager.getScreenWidth(this);
                lp.width = screenWidth / 2 ;
                lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }else if(mOrientation == OrientationHelper.HORIZONTAL){
                int screenHeight = DeviceManager.getScreenHeight(this);
                lp.width = RecyclerView.LayoutParams.WRAP_CONTENT;
                lp.height = screenHeight / 2;
            }

        }else if(mLayoutManager instanceof StaggeredGridLayoutManager){
            int screenWidth = DeviceManager.getScreenWidth(this);
            int screenHeight = DeviceManager.getScreenHeight(this);
            Random random = new Random();

            if(mOrientation == OrientationHelper.VERTICAL){
                lp.width = screenWidth / 4 + random.nextInt(screenWidth/4);
                lp.height = screenHeight/2 + random.nextInt(screenHeight/2);
            }else if(mOrientation == OrientationHelper.HORIZONTAL){
                lp.width = screenWidth / 2 + random.nextInt(screenWidth/2);
                lp.height = screenHeight/4 + random.nextInt(screenHeight/4);
            }

        }

        itemView.setLayoutParams(lp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_manager_option_menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_normal :
                isReverse = false ;
                break;
            case R.id.action_reverse :
                isReverse = true ;
                break;
            case R.id.action_vertical_linear :
                mOrientation = OrientationHelper.VERTICAL ;
                mLayoutManager = new LinearLayoutManager(this , LinearLayout.VERTICAL , false);
                break;
            case R.id.action_horizontal_linear:
                mOrientation = OrientationHelper.HORIZONTAL ;
                mLayoutManager = new LinearLayoutManager(this , LinearLayout.HORIZONTAL , false);
                break;
            case R.id.action_vertical_grid:
                mOrientation = OrientationHelper.VERTICAL ;
                mLayoutManager = new GridLayoutManager(this , 2 ,
                        LinearLayout.VERTICAL , false);
                break;
            case R.id.action_horizontal_grid:
                mOrientation = OrientationHelper.HORIZONTAL ;
                mLayoutManager = new GridLayoutManager(this , 2 ,
                        LinearLayout.HORIZONTAL , false);
                break;
            case R.id.action_vertical_staggered:
                mOrientation = OrientationHelper.VERTICAL ;
                mLayoutManager = new StaggeredGridLayoutManager(2 , OrientationHelper.VERTICAL);
                break;
            case R.id.action_horizontal_staggered:
                mOrientation = OrientationHelper.HORIZONTAL ;
                mLayoutManager = new StaggeredGridLayoutManager(2 , OrientationHelper.HORIZONTAL);
                break;

        }

       if(mLayoutManager instanceof LinearLayoutManager){
           ((LinearLayoutManager)mLayoutManager).setReverseLayout(isReverse);
       }else if(mLayoutManager instanceof GridLayoutManager){
           ((GridLayoutManager)mLayoutManager).setReverseLayout(isReverse);
       }

        mItemDecoration.setOrientation(mOrientation);
        mRecyclerView.setLayoutManager(mLayoutManager);

        return true ;
    }


}
