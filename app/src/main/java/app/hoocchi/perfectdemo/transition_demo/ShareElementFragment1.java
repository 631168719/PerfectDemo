package app.hoocchi.perfectdemo.transition_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareElementFragment1 extends Fragment {

    private ImageView mCircle ;

    public ShareElementFragment1() {
        // Required empty public constructor
    }

    public static ShareElementFragment1 newInstance(){
        return new ShareElementFragment1();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_share_element_fragment1, container, false);

        mCircle = (ImageView) root.findViewById(R.id.blue_circle);
        DrawableCompat.setTint(mCircle.getDrawable() ,
                ContextCompat.getColor(getActivity() , R.color.ball_blue));

        root.findViewById(R.id.overlap_false).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNextFragment(false);
            }
        });

        root.findViewById(R.id.overlap_true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNextFragment(true);
            }
        });

        return root ;
    }

    private void addNextFragment(boolean overlap){
        ShareElementFragment2 fragment2 = ShareElementFragment2.newInstance();

        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(500);
        fragment2.setEnterTransition(slide);

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(500);

        fragment2.setSharedElementEnterTransition(changeBounds);

        fragment2.setAllowEnterTransitionOverlap(overlap);
        fragment2.setAllowReturnTransitionOverlap(overlap);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.share_element_content , fragment2)
                //Fragment之间执行共享动画时需要调用该方法
                .addSharedElement(mCircle , mCircle.getTransitionName())
                .addToBackStack(null)
                .commit();
    }

}
