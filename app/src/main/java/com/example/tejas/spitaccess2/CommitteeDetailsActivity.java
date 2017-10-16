package com.example.tejas.spitaccess2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.Adapters.CommitteeDetails;
import com.example.tejas.spitaccess2.Adapters.CommitteeMember;
import com.example.tejas.spitaccess2.Adapters.CommitteeMemberRecyclerAdapter;

import java.util.ArrayList;

public class CommitteeDetailsActivity extends AppCompatActivity {

    TextView title,description;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getIntent().getStringExtra("initials"));
        setContentView(R.layout.activity_committee_details);

        title = (TextView) findViewById(R.id.CommitteeActivity_Title);
        description = (TextView) findViewById(R.id.CommitteeActivity_Description);
        recyclerView = (RecyclerView) findViewById(R.id.CommitteeActivity_Recycler);

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));

        int numberOfMembers=getIntent().getIntExtra("NumberOfMembers",0);

        ArrayList<CommitteeMember> committeeMembers=new ArrayList<CommitteeMember>();
        String membernames[]=new String[numberOfMembers];
        String memberdesignations[]=new String[numberOfMembers];

        for (int i=0;i<=numberOfMembers-1;i++)
        {
            Log.v(String.valueOf(i),getIntent().getStringExtra("membername"+String.valueOf(i)));
            Log.v(String.valueOf(i)+"De",getIntent().getStringExtra("memberdesignation"+String.valueOf(i)));
            membernames[i]=getIntent().getStringExtra("membername"+String.valueOf(i));
            memberdesignations[i]=getIntent().getStringExtra("memberdesignation"+String.valueOf(i));
            committeeMembers.add(new CommitteeMember(membernames[i],memberdesignations[i],getIntent().getStringExtra("memberemail"+String.valueOf(i)),getIntent().getStringExtra("memberphone"+String.valueOf(i))));
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommitteeMemberRecyclerAdapter(committeeMembers,this));
    }
}
