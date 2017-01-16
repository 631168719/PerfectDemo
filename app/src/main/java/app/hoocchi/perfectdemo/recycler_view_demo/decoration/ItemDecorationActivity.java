package app.hoocchi.perfectdemo.recycler_view_demo.decoration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import app.hoocchi.perfectdemo.DataCenter;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.recycler_view_demo.adapter.CommonAdapter;

public class ItemDecorationActivity extends AppCompatActivity {

    private static final String TYPE = "type";
    private static final int DEFAULT = 0 ;
    private static final int DEFAULT_WITH_PADDING = 1 ;
    private static final int CUSTOM_DIVIDER = 2 ;
    private static final int DRAW_DIVIDER_LINE = 3 ;
    private static final int DRAW_DIVIDER_RECT = 4 ;

    private RecyclerView mRecyclerView ;

    private int mType ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initToolBar();

        initRecyclerView();

        addItemDecoration();
    }

    private void addItemDecoration() {
        mType = getIntent().getIntExtra(TYPE , DEFAULT);

        CommonItemDecoration itemDecoration = new CommonItemDecoration(this , CommonItemDecoration.VERTICAL);

        switch(mType){
            case DEFAULT :
                itemDecoration.setPadding(0);
                itemDecoration.setColor(Color.YELLOW);
                itemDecoration.setSize(4);
                break;
            case DEFAULT_WITH_PADDING :
                itemDecoration.setPadding(20);
                itemDecoration.setSize(6);
                itemDecoration.setColor(Color.BLACK);
                break;
            case CUSTOM_DIVIDER :
                itemDecoration.setDividerDrawable(this , R.drawable.item_divider);
                itemDecoration.setColor(Color.RED);
                itemDecoration.setPadding(20);
                itemDecoration.setSize(8);
                break;
            case DRAW_DIVIDER_LINE :
                itemDecoration = new CommonItemDecoration(CommonItemDecoration.VERTICAL);
                break;
            case DRAW_DIVIDER_RECT :
                itemDecoration = new CommonItemDecoration(CommonItemDecoration.VERTICAL);
                itemDecoration.setColor(Color.YELLOW);
                itemDecoration.setSize(4);
                break;
        }

        mRecyclerView.addItemDecoration(itemDecoration);

    }

    private void initToolBar() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.tool_bar);
        toolBar.setTitle("ItemDecoration");
        setSupportActionBar(toolBar);
    }

    private void initRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

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
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();

        if(layoutManager instanceof LinearLayoutManager){
            lp.width = RecyclerView.LayoutParams.MATCH_PARENT;
            lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        }

        itemView.setLayoutParams(lp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_decoration_option_menu , menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_default_divider :
                mType = DEFAULT ;
                break;
            case R.id.action_divider_with_padding :
                mType = DEFAULT_WITH_PADDING ;
                 break;
            case R.id.action_custom_divider:
                mType = CUSTOM_DIVIDER ;
                break;
            case R.id.action_draw_line:
                mType = DRAW_DIVIDER_LINE ;
                break;
            case R.id.action_draw_rect :
                mType = DRAW_DIVIDER_RECT ;
                break;
        }

        Intent i = new Intent(this , ItemDecorationActivity.class);
        i.putExtra(TYPE , mType);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

        return true ;
    }
}
