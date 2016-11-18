package app.hoocchi.perfectdemo.material_demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.hoocchi.perfectdemo.R;

/**
 * Created by st on 2016/11/18.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private String [] mStrArrays ;

    public RecyclerAdapter(String [] arrays){
        this.mStrArrays = arrays ;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_recycler_item , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(mStrArrays[position]);
    }

    @Override
    public int getItemCount() {
        return mStrArrays.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mText ;

        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView;
        }
    }
}
