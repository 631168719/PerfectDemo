package app.hoocchi.perfectdemo.recycler_view_demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *  通用适配器
 * Created by st on 2017/1/10.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private List<T> mDatas ;

    public CommonAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    protected abstract int getLayoutId(int viewType);
    protected abstract void convert(ViewHolder holder , T data , int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.getViewHolder(parent , getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder , mDatas.get(position) , position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private View mItemView ;
        private SparseArray<View> mViewArrays ;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView ;
            mViewArrays = new SparseArray<>();
        }

        public static ViewHolder getViewHolder(ViewGroup parent , int layoutId){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(layoutId , parent , false);

            return new ViewHolder(itemView);
        }

        public <T extends View> T getChildView(int childId){
            View childView = mViewArrays.get(childId);

            if(childView == null){
                childView = mItemView.findViewById(childId);
                mViewArrays.put(childId , childView);
            }

            return (T)childView;
        }

        public void setText(int childId , String text){
            TextView view = getChildView(childId);
            view.setText(text);
        }

        public void setImageResource(int childId , int resId){
            ImageView view = getChildView(childId);
            view.setImageResource(resId);
        }

        public void setIdClickListener(int childId , View.OnClickListener listener){
            View view = getChildView(childId);
            view.setOnClickListener(listener);
        }
    }
}
