package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.Adapters.CommitteeDetails;
import com.example.tejas.spitaccess2.Adapters.CommitteeMember;
import com.example.tejas.spitaccess2.Adapters.CommitteeRecyclerAdapter;
import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommitteeListFragment extends Fragment {


    public CommitteeListFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_committee_list, container, false);
        // Inflate the layout for this fragment

        recyclerView=(RecyclerView)view.findViewById(R.id.CommitteeListFragment_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<CommitteeMember> csimembers=new ArrayList<CommitteeMember>();
        csimembers.add(new CommitteeMember("Aditya Gaikwad","Secretary","abc@gmail.com","9930209301"));
        csimembers.add(new CommitteeMember("Aditya Desai","Vice-Secretary","abc@gmail.com",null));
        csimembers.add(new CommitteeMember("Meet Gopani","Financial Head","abc@gmail.com","9930209301"));

        ArrayList<CommitteeMember> facemembers=new ArrayList<CommitteeMember>();
        facemembers.add(new CommitteeMember("Jainam Soni","Secretary",null,null));
        facemembers.add(new CommitteeMember("Priyansh Acharay","Technical",null,"9930209301"));
        facemembers.add(new CommitteeMember("Hussain","Technical Head","hbgmail.com",null));


        ArrayList<CommitteeDetails> details=new ArrayList<CommitteeDetails>();
        details.add(new CommitteeDetails("CSI","Computer Society of India","CSI Description (Content)",csimembers,R.drawable.csilogo2));
        details.add(new CommitteeDetails("FACE","Forum of Aspiring Computer Engineers","FACE Description (Content)",facemembers,R.drawable.spitlogo));

        recyclerView.setAdapter(new CommitteeRecyclerAdapter(details,getActivity()));
        return view;
    }

}
