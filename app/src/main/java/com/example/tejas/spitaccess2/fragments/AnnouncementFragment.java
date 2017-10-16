package com.example.tejas.spitaccess2.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.Adapters.AnnouncementItem;
import com.example.tejas.spitaccess2.Adapters.AnnouncementsRecyclerAdapter;
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
public class AnnouncementFragment extends Fragment {


    public AnnouncementFragment() {
        // Required empty public constructor
    }
    FirebaseDatabase databaseAnnouncement;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_announcement, container, false);

        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            //Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();

        } else  {
           try
           {
               Snackbar.make((LinearLayout)view.findViewById(R.id.AnnouncementFragement_LinearContainer),"No Internet Connection",Snackbar.LENGTH_LONG).show();
           }catch (Exception e){
               Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
           }
        }
        recyclerView=(RecyclerView)view.findViewById(R.id.AnnouncementFragement_RecyclerContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ArrayList<AnnouncementItem> announcements=new ArrayList<AnnouncementItem>();

        databaseAnnouncement = FirebaseDatabase.getInstance();
        DatabaseReference myRef = databaseAnnouncement.getReference("announcements");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                announcements.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //ArrayList<EventDetail> values=(ArrayList<EventDetail>) postSnapshot.getValue();

                    AnnouncementItem announcementItem=postSnapshot.getValue(AnnouncementItem.class);
                    //words.add(new Word(post1.Title, post1.Description, post1.url,post1.newtag));
                    //adapter.notifyDataSetChanged();
                    announcements.add(announcementItem);
                    Log.v("Capital",""+announcements.size());
                    recyclerView.setAdapter(new AnnouncementsRecyclerAdapter(announcements,getActivity()));
                    //recyclerView.setAdapter(new RecyclerViewAdapter(eventDetail,MainActivity.this));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
