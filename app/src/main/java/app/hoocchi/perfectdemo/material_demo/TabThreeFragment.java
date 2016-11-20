package app.hoocchi.perfectdemo.material_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabThreeFragment extends Fragment implements View.OnClickListener{


    private Button mBtnBehavior ;
    private Button mBtnDialog ;
    private Button mBtnFragment ;

    private BottomSheetBehavior<View> mBehavior ;
    private BottomSheetDialog mDialog ;

    public TabThreeFragment() {
        // Required empty public constructor
    }

    public static TabThreeFragment newInstance(){
        return new TabThreeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnBehavior = (Button) view.findViewById(R.id.btn_behavior);
        mBtnDialog = (Button) view.findViewById(R.id.btn_dialog);
        mBtnFragment = (Button) view.findViewById(R.id.btn_fragment);

        mBtnBehavior.setOnClickListener(this);
        mBtnDialog.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);

        mBehavior = BottomSheetBehavior.from(view.findViewById(R.id.bottom_sheet_behavior));

        View contentView = View.inflate(getActivity() , R.layout.bottom_sheet_dialog_content , null);
        mDialog = new BottomSheetDialog(getActivity());
        mDialog.setContentView(contentView);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_behavior :
               if(mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                   mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
               }else{
                   mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
               }
                break;
            case R.id.btn_dialog :
                if(mDialog.isShowing()){
                    mDialog.dismiss();
                }else{
                    mDialog.show();
                }
                break;
            case R.id.btn_fragment :
                break;
        }
    }
}
