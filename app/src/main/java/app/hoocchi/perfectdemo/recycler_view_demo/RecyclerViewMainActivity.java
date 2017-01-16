package app.hoocchi.perfectdemo.recycler_view_demo;

import android.os.Bundle;
import android.view.View;

import app.hoocchi.perfectdemo.BaseActivity;
import app.hoocchi.perfectdemo.R;

public class RecyclerViewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_main);
        getSupportActionBar().setTitle("RecyclerView Demo");
    }

    public void jump(View v){
        switch(v.getId()){
            case R.id.layout_manager :
                jumpActivity(LayoutManagerActivity.class);
                break;
            case R.id.item_decoration :
                jumpActivity(ItemDecorationActivity.class);
                break;
        }
    }
}
