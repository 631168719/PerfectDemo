package app.hoocchi.perfectdemo.transition_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareElementFragment2 extends Fragment {


    public ShareElementFragment2() {
        // Required empty public constructor
    }

    public static ShareElementFragment2 newInstance(){
        return new ShareElementFragment2();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_element_fragment2, container, false);
    }

}
