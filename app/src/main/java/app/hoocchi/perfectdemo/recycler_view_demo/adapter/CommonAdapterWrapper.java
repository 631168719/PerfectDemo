package app.hoocchi.perfectdemo.recycler_view_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 可以设置HeaderView以及FooterView的适配器
 * Created by st on 2017/1/16.
 */
public class CommonAdapterWrapper extends RecyclerView.Adapter<CommonAdapter.ViewHolder>{

    private CommonAdapter mAdapter ;
    private View mHeaderView ;
    private View mFooterView ;

    public enum ItemType{
        HEADER , NORMAL , FOOTER
    }

    public CommonAdapterWrapper(CommonAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void setHeaderView(View view){
        this.mHeaderView = view ;
    }

    public void setFooterView(View view){
        this.mFooterView = view ;
    }

    @Override
    public int getItemViewType(int position) {
       if(position == 0){
           return mHeaderView == null ? ItemType.NORMAL.ordinal()
                   : ItemType.HEADER.ordinal();
       }else if(position == mAdapter.getItemCount() + 1){
           return mFooterView == null ? ItemType.NORMAL.ordinal()
                   : ItemType.FOOTER.ordinal();
       }else {
           return ItemType.NORMAL.ordinal() ;
       }
    }

    @Override
    public CommonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ItemType.HEADER.ordinal()){
            return new CommonAdapter.ViewHolder(mHeaderView);
        }else if(viewType == ItemType.FOOTER.ordinal()){
            return new CommonAdapter.ViewHolder(mFooterView);
        }else {
            return mAdapter.onCreateViewHolder(parent , viewType);
        }
    }

    @Override
    public void onBindViewHolder(CommonAdapter.ViewHolder holder, int position) {
       if(position == 0 && mHeaderView != null){
           return ;
       }else if(position == mAdapter.getItemCount() + 1){
           return ;
       }else {
           mAdapter.onBindViewHolder(holder , position);
       }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount()
                + (mHeaderView == null ? 0 : 1)
                + (mFooterView == null ? 0 : 1) ;
    }
}
