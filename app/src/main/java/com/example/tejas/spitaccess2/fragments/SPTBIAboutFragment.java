package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SPTBIAboutFragment extends Fragment {


    public SPTBIAboutFragment() {
        // Required empty public constructor
    }

    CardView incubatecard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sptbiabout, container, false);
        incubatecard=(CardView)view.findViewById(R.id.SPTBI_AboutOurIncubateesCard);

        incubatecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.SPTBI_FrameContainer,new SPTBIIncubateesFragment()).addToBackStack(null).commit();

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
