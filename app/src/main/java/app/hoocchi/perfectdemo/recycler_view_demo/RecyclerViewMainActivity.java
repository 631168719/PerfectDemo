package app.hoocchi.perfectdemo.recycler_view_demo;

import android.os.Bundle;
import android.view.View;

import app.hoocchi.perfectdemo.BaseActivity;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.recycler_view_demo.adapter.CommonAdapterActivity;
import app.hoocchi.perfectdemo.recycler_view_demo.decoration.ItemDecorationActivity;
import app.hoocchi.perfectdemo.recycler_view_demo.layout_manager.LayoutManagerActivity;

public class RecyclerViewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        getSupportActionBar().setTitle("RecyclerView Demo");
    }

    public void jump(View v){
        switch(v.getId()){
            case R.id.common_adapter :
                jumpActivity(CommonAdapterActivity.class);
                break;
            case R.id.layout_manager :
                jumpActivity(LayoutManagerActivity.class);
                break;
            case R.id.item_decoration :
                jumpActivity(ItemDecorationActivity.class);
                break;
        }
    }
}
