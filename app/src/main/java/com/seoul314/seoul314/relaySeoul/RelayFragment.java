package com.seoul314.seoul314.relaySeoul;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.seoul314.seoul314.R;

import java.util.logging.Logger;


/**
 * A simple {@link Fragment} subclass.
 */
public class RelayFragment extends Fragment {

    public static final String TAG="edu.android";


    private Button startBtn;  // 릴레이 스타트 버튼

    public RelayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_relay, container, false);
    }

    @Override
    public void onStart() {
        Log.i(TAG,"fragment onStart");
        super.onStart();
        View view=getView();
        startBtn = view.findViewById(R.id.relay_btn_start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
