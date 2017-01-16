package app.hoocchi.perfectdemo.recycler_view_demo.adapter;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.hoocchi.perfectdemo.BaseActivity;
import app.hoocchi.perfectdemo.DataCenter;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.recycler_view_demo.DisplayItem;
import app.hoocchi.perfectdemo.recycler_view_demo.decoration.DefaultItemDecoration;

public class CommonAdapterActivity extends BaseActivity {

    private RecyclerView mRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        setBarTitle("Common Adapter");

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(this , OrientationHelper.VERTICAL));
        //单类型
        mRecyclerView.setAdapter(mSingleAdapter);

    }

    private List<DisplayItem> getDataList(){
        List<DisplayItem> list = new ArrayList<>();

        for(int i = 0 ; i < DataCenter.mStrArrays.length ; i++){
            DisplayItem item = new DisplayItem();
            item.setText(DataCenter.mStrArrays[i]);
            int index = i % DataCenter.mImgIds.length ;
            item.setImgId(DataCenter.mImgIds[index]);

            list.add(item);
        }

        return list ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adapter_option_menu, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_single_type :
                mRecyclerView.setAdapter(mSingleAdapter);
                break;
            case R.id.action_multi_type:
                mRecyclerView.setAdapter(mMultiAdapter);
                break;
            case R.id.action_head_foot_type:
                mWrapperAdapter.setHeaderView(getView("HeaderView"));
                mWrapperAdapter.setFooterView(getView("FooterView"));
                mRecyclerView.setAdapter(mWrapperAdapter);
                break;
            case R.id.action_spinned_type:
                break;
        }

        return true ;
    }

    private View getView(String text){
        View view = LayoutInflater.from(this).inflate(R.layout.header_footer_recycler_item , null);
        TextView textView = (TextView) view.findViewById(R.id.header_footer_view);
        textView.setText(text);

        return view ;
    }

    /**
     * 单类型Adapter
     */
    private CommonAdapter<DisplayItem> mSingleAdapter = new CommonAdapter<DisplayItem>(getDataList()) {
        @Override
        protected int getLayoutId(int viewType) {
            return R.layout.text_recycler_item;
        }

        @Override
        protected void convert(ViewHolder holder, DisplayItem data, int position) {
            TextView child = holder.getChildView(R.id.item_text);
            child.setText(data.getText());
        }
    };

    /**
     * 多类型Adapter
     */
    private CommonAdapter<DisplayItem> mMultiAdapter = new CommonAdapter<DisplayItem>(getDataList()) {

        private static final int IMAGE = 0 ;
        private static final int TEXT = 1 ;

        @Override
        protected int getLayoutId(int viewType) {
            switch(viewType){
                case IMAGE :
                    return R.layout.image_recycler_item ;
                case TEXT :
                    return R.layout.text_recycler_item ;
                default:
                    return R.layout.image_recycler_item ;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(position % 2 == 0){
                return IMAGE;
            }else{
                return TEXT;
            }
        }

        @Override
        protected void convert(ViewHolder holder, DisplayItem data, int position) {
            int viewType = getItemViewType(position);

            switch(viewType){
                case IMAGE :
                    ImageView image = holder.getChildView(R.id.item_icon);
                    image.setBackgroundResource(data.getImgId());
                    break;
                case TEXT :
                    TextView text = holder.getChildView(R.id.item_text);
                    text.setText(data.getText());
                    break;
            }

        }
    };

    /**
     * 可以设置HeaderView以及FooterView的Adapter
     */
    private CommonAdapterWrapper mWrapperAdapter = new CommonAdapterWrapper(mSingleAdapter);
}
