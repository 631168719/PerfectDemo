package app.hoocchi.perfectdemo.transition_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.hoocchi.perfectdemo.DialogManager;
import app.hoocchi.perfectdemo.R;
import app.hoocchi.perfectdemo.VersionManager;

public class TransitionMainActivity extends TransitionBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_main);

        if(VersionManager.isLowerLolipop()){
            DialogManager.showAlertDialog(this, "Transition Demo只能在Android 5.0+的手机上运行噢！",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            return ;
        }

        setupToolBar("Transition Demo");
        setupWindowTransition();
        setupLayout();
    }

    private void setupWindowTransition() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(500);

        getWindow().setExitTransition(slide);
        //如果不设置Reenter动画，则默认使用Exit的反转动画
//        getWindow().setReenterTransition(slide);
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter());
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<TransitionItem> mItems;

        public RecyclerAdapter() {
            mItems = new ArrayList<>();
            mItems.add(new TransitionItem(R.color.ball_red, "Transitions"));
            mItems.add(new TransitionItem(R.color.ball_blue, "Shared Elements"));
            mItems.add(new TransitionItem(R.color.ball_green, "Scene Animations"));
            mItems.add(new TransitionItem(R.color.ball_purple, "Custom Transitions"));
            mItems.add(new TransitionItem(R.color.ball_yellow, "Circular Reveal Animations"));
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.transition_demo_recycler_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            final MyViewHolder myHolder = (MyViewHolder) holder;
            TransitionItem item = mItems.get(position);

            String iconTransitionName = getResources().getString(R.string.share_element_icon);
            String titleTransitionName = getResources().getString(R.string.share_element_title);

            myHolder.mItemIcon.setTransitionName(iconTransitionName);
            myHolder.mItemTitle.setTransitionName(titleTransitionName);

//            DrawableCompat.setTint(myHolder.mItemIcon.getDrawable() ,
//                    ContextCompat.getColor(TransitionMainActivity.this , item.getColor()));

            ColorStateList state = ColorStateList.valueOf(
                    ContextCompat.getColor(TransitionMainActivity.this , item.getColor()));
            myHolder.mItemIcon.setImageTintList(state);
            myHolder.mItemTitle.setText(item.getTitle());

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Pair[] pairs;
                    switch (myHolder.getAdapterPosition()) {
                        case 0:
                            pairs = TransitionHelper.createSafeTransitionPairs(TransitionMainActivity.this,
                                    true);
                            showActivity(TransitionActivity1.class, pairs);
                            break;
                        case 1:
                            pairs = TransitionHelper.createSafeTransitionPairs(TransitionMainActivity.this,
                                    false,
                                    new Pair<>(myHolder.mItemIcon, myHolder.mItemIcon.getTransitionName()),
                                    new Pair<>(myHolder.mItemTitle, myHolder.mItemTitle.getTransitionName()));

                            showActivity(ShareElementActivity.class , pairs);
                            break;
                        case 2:
                            pairs = TransitionHelper.createSafeTransitionPairs(TransitionMainActivity.this ,
                                    true);
                            showActivity(SceneAnimationActivity.class , pairs);
                            break;
                        case 3:
                            pairs = TransitionHelper.createSafeTransitionPairs(TransitionMainActivity.this ,
                                    true);
                            showActivity(CustomTransitionActivity.class , pairs);
                            break;
                        case 4 :
                            pairs = TransitionHelper.createSafeTransitionPairs(TransitionMainActivity.this ,
                                    false ,
                                    new Pair<>(myHolder.mItemIcon , myHolder.mItemIcon.getTransitionName()));
                            showActivity(CircularRevealActivity.class , pairs);
                            break;
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView mItemIcon;
            public TextView mItemTitle;

            public MyViewHolder(View itemView) {
                super(itemView);

                mItemIcon = (ImageView) itemView.findViewById(R.id.item_icon);
                mItemTitle = (TextView) itemView.findViewById(R.id.item_title);
            }
        }
    }

    private void showActivity(Class target, Pair<View, String>[] pairs) {
        Intent i = new Intent(this, target);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, options.toBundle());
    }
}
