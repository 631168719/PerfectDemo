package app.hoocchi.perfectdemo.material_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.hoocchi.perfectdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTwoFragment extends Fragment {

    private TextInputLayout mInputLayout1 ;
    private TextInputLayout mInputLayout2 ;
    private TextInputLayout mInputLayout3 ;

    public TabTwoFragment() {
        // Required empty public constructor
    }

    public static TabTwoFragment newInstance(){
        return new TabTwoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInputLayout1 = (TextInputLayout) view.findViewById(R.id.text_input_layout1);
        mInputLayout2 = (TextInputLayout) view.findViewById(R.id.text_input_layout2);
        mInputLayout3 = (TextInputLayout) view.findViewById(R.id.text_input_layout3);

        mInputLayout2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() >= 16){
                    mInputLayout2.setErrorEnabled(true);
                    mInputLayout2.setError("您输入的密码长度不对!");
                }else{
                    mInputLayout2.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mInputLayout3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() >= mInputLayout3.getCounterMaxLength()){
//                    mInputLayout3.setErrorEnabled(true);
//                    mInputLayout3.setError("您输入的邮箱长度不对!");
                }else{
//                    mInputLayout3.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}
