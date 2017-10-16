package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.Adapters.DepartmentDetail;
import com.example.tejas.spitaccess2.Adapters.DepartmentRecyclerAdapter;
import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentFragment extends Fragment {


    public DepartmentFragment() {
        // Required empty public constructor
    }

    RecyclerView departmentdetailsrecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_department, container, false);
        departmentdetailsrecycler=(RecyclerView)view.findViewById(R.id.DepartmentFragment_RecyclerView);

        Log.v("Started","dept frag");
        departmentdetailsrecycler.setHasFixedSize(true);
        departmentdetailsrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<DepartmentDetail> departmentDetails=new ArrayList<DepartmentDetail>();

        departmentDetails.add(new DepartmentDetail("Department of Electronics Engineering","B.Tech Electronics Engineering\nPh.D Electronics Engineering","60 intake\n05 intake","Dr. S.S. Rathod","surendra_rathod@spit.ac.in","http://etrx.spit.ac.in/","#C62828"));
        departmentDetails.add(new DepartmentDetail("Department of Electronics and Telecommunications Engineering","B.Tech Electronics and \nTelecommunications Engineering\nM.Tech (Electronics and \nTelecommunications Engineering)\nPh.D Electronics and Telecommunication","60 intake\n\n18 intake\n\n10 intake","Dr. Y.S Rao","ysrao@spit.ac.in","http://extc.spit.ac.in/","#FFD700"));
        departmentDetails.add(new DepartmentDetail("Department of Computer Engineering","B.Tech Computer Engineering\nM.Tech (Computer Engineering)\nPh.D Computer Engineering","60 Intake\n18 Intake\n20 Intake","Dr. Dhananjay Kalbande","drkalbande@spit.ac.in","http://comp.spit.ac.in/","#36648B"));
        departmentDetails.add(new DepartmentDetail("Department of Information Technology","B.Tech Information Technology","60 intake","Dr.(Mrs.) Radha Shankarmani","radha_shankarmani@spit.ac.in","http://it.spit.ac.in/","#008744"));
        departmentDetails.add(new DepartmentDetail("Department of Applied Science and Humanities","Offers first year courses to all UG programmes","","Dr. Rita Das",null,"http://ash.spit.ac.in/","#CB5700"));
        departmentDetails.add(new DepartmentDetail("Department of Masters in Computer Applications","Master of Computer Applications(MCA)\nPh.D Master of Computer Applications","60 intake\n05 intake","Dr. Pooja Raundale","poojaraundale@spit.ac.in","http://mca.spit.ac.in/","#36B0B6"));

        departmentdetailsrecycler.setAdapter(new DepartmentRecyclerAdapter(departmentDetails,getActivity()));
        return view;
    }

}
