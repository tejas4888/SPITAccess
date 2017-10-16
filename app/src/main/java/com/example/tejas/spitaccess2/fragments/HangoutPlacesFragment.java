package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.Adapters.HangoutItemAdapter;
import com.example.tejas.spitaccess2.Adapters.HangoutRecyclerAdapter;
import com.example.tejas.spitaccess2.MainActivity;
import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutPlacesFragment extends Fragment {


    public HangoutPlacesFragment() {
        // Required empty public constructor
    }

    RecyclerView HangoutFragment_RecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hangout_places, container, false);

        HangoutFragment_RecyclerView=(RecyclerView)view.findViewById(R.id.HangoutFragment_RecyclerView);

        HangoutFragment_RecyclerView.setHasFixedSize(true);
        HangoutFragment_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<HangoutItemAdapter> allplaces=new ArrayList<HangoutItemAdapter>();

        //geo:19.1249757,72.83722?q=Dominoes AZ3 Metro Station
        allplaces.add(new HangoutItemAdapter("Dominoes","Outside campus, Azad Nagar Metro Station","https://www.zomato.com/mumbai/vrindavan-azad-nagar/menu","https://goo.gl/maps/sZfxkrxC2rJ2","9930209301",R.drawable.dominos1));
        allplaces.add(new HangoutItemAdapter("Hotel Vrindavan","Outside campus, Opp. Andheri Sports Complex","https://www.zomato.com/mumbai/vrindavan-azad-nagar/menu","geo:19.1248021,72.8374248?q=Vrindavan Bhavans"," 02226714872",R.drawable.vrindavan));
        HangoutFragment_RecyclerView.setAdapter(new HangoutRecyclerAdapter(allplaces,getActivity()));

        return view;
    }

}
