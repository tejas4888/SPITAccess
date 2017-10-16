package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.Adapters.EventDetail;
import com.example.tejas.spitaccess2.Adapters.RecyclerViewAdapter;
import com.example.tejas.spitaccess2.Adapters.SPTBIItem;
import com.example.tejas.spitaccess2.Adapters.SPTBIRecyclerAdapter;
import com.example.tejas.spitaccess2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SPTBIIncubateesFragment extends Fragment {

    FirebaseDatabase database3;
    public SPTBIIncubateesFragment() {
        // Required empty public constructor
    }

    RecyclerView incubateesRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sptbiincubatees, container, false);

        incubateesRecyclerView=(RecyclerView)view.findViewById(R.id.SPTBI_IncubateesRecyclerView);
        incubateesRecyclerView.setHasFixedSize(true);
        incubateesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Inflate the layout for this fragment

        final ArrayList<SPTBIItem> allstartups=new ArrayList<SPTBIItem>();

        database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database3.getReference("sptbi");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                allstartups.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //ArrayList<EventDetail> values=(ArrayList<EventDetail>) postSnapshot.getValue();

                    SPTBIItem startup=postSnapshot.getValue(SPTBIItem.class);
                    //words.add(new Word(post1.Title, post1.Description, post1.url,post1.newtag));
                    //adapter.notifyDataSetChanged();
                    allstartups.add(startup);
                    Log.v("Capital",""+allstartups.size());
                    incubateesRecyclerView.setAdapter(new SPTBIRecyclerAdapter(allstartups,getActivity()));
                    //recyclerView.setAdapter(new RecyclerViewAdapter(eventDetail,MainActivity.this));
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });


        return view;
    }

}
