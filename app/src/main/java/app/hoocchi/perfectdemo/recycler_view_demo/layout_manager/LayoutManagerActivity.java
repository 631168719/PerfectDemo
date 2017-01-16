package app.hoocchi.perfectdemo.recycler_view_demo.layout_manager;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

import app.hoocchi.perfectdemo.BaseActivity;
import app.hoocchi.perfectdemo.DataCenter;
import app.hoocchi.perfectdemo.DeviceManager;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.recycler_view_demo.adapter.CommonAdapter;

public class LayoutManagerActivity extends BaseActivity {

    private RecyclerView mRecyclerView ;
    private RecyclerView.LayoutManager mLayoutManager;

    private boolean isReverse ;
    private int mOrientation ;

    private Menu mMenu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        setBarTitle("Layout Manager");

        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mOrientation = OrientationHelper.VERTICAL;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        CommonAdapter<String> adapter = new CommonAdapter<String>(Arrays.asList(DataCenter.mStrArrays)) {

            @Override
            protected int getLayoutId(int viewType) {
                return R.layout.text_recycler_item;
            }

            @Override
            protected void convert(ViewHolder holder, String data, int position) {
                setItemLayoutParams(holder.itemView , position);

                TextView mText = holder.getChildView(R.id.item_text);
                mText.setText(data);
            }

        };

        mRecyclerView.setAdapter(adapter);

    }

    private void setItemLayoutParams(View itemView , int position){
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        int margins = DeviceManager.dpToPx(this , 10);
        lp.setMargins(margins , margins , margins , margins);

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

            if(mOrientation == OrientationHelper.VERTICAL){
                int screenWidth = DeviceManager.getScreenWidth(this);
                if(position % 2 == 0){
                    lp.width = screenWidth / 2;
                }else{
                    lp.width = screenWidth / 3 ;
                }
                lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            }else if(mOrientation == OrientationHelper.HORIZONTAL){
                int screenHeight = DeviceManager.getScreenHeight(this);
                lp.width = RecyclerView.LayoutParams.WRAP_CONTENT;
                if(position % 2 == 0){
                    lp.height = screenHeight / 3;
                }else{
                    lp.height = screenHeight / 4 ;
                }
            }

        }

        itemView.setLayoutParams(lp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_manager_option_menu , menu);
        mMenu = menu ;
        return true ;
    }

    private void showHideItem(boolean visible){
        mMenu.findItem(R.id.action_normal).setVisible(visible);
        mMenu.findItem(R.id.action_reverse).setVisible(visible);
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

       showHideItem(!(mLayoutManager instanceof StaggeredGridLayoutManager));

        mRecyclerView.setLayoutManager(mLayoutManager);

        return true ;
    }


}
