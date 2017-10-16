package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tejas.spitaccess2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment_Tab2 extends Fragment {

    TextView visiontext;
    TextView missiontext;

    public HomeFragment_Tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_fragment__tab2, container, false);
        // Inflate the layout for this fragment

        visiontext=(TextView)view.findViewById(R.id.HomeFragment_Tab2Vision);
        visiontext.setText(R.string.HomeFragmentVisionText);

        missiontext=(TextView)view.findViewById(R.id.HomeFragment_Tab2Mission);
        missiontext.setText(R.string.HomeFragmentMissionText);
        return view;
    }

}
