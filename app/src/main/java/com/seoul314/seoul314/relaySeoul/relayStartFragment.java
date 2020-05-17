package com.seoul314.seoul314.relaySeoul;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seoul314.seoul314.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class relayStartFragment extends Fragment {


    public relayStartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relay_start, container, false);
    }

}
