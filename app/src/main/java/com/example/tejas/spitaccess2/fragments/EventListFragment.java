package com.example.tejas.spitaccess2.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tejas.spitaccess2.Adapters.EventDetail;
import com.example.tejas.spitaccess2.Adapters.RecyclerViewAdapter;
import com.example.tejas.spitaccess2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {

    FirebaseDatabase database;
    FloatingActionButton floatingActionButton;
    static int firebaseDatabaseCreatedChecker=0;
    public EventListFragment() {
        // Required empty public constructor
    }

    RecyclerView EventListFragment_RecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_event_list,container,false);

        ConnectivityManager conMan = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            //Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();

        } else  {
            Snackbar.make((CoordinatorLayout)view.findViewById(R.id.EventListFragment_CoordinatorLayout),"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }

        EventListFragment_RecyclerView=(RecyclerView)view.findViewById(R.id.EventListFragment_RecyclerView);
        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.EventListFragment_FloatingButton);

        EventListFragment_RecyclerView.setHasFixedSize(true);
        EventListFragment_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://goo.gl/forms/1xIcB1VfR7fFCTUL2"));
                getActivity().startActivity(i);
            }
        });

        if (firebaseDatabaseCreatedChecker==0) {
            Log.v("Here","i="+firebaseDatabaseCreatedChecker);
            database = FirebaseDatabase.getInstance();
            //database.setPersistenceEnabled(false);
            firebaseDatabaseCreatedChecker=1;
        }
        else
        {
            Log.v("Else","i="+firebaseDatabaseCreatedChecker);
            database=FirebaseDatabase.getInstance();
        }
        //database.setPersistenceEnabled(false);


        DatabaseReference myRef = database.getReference("events");

        final ArrayList<EventDetail> allevent=new ArrayList<EventDetail>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                allevent.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    //ArrayList<EventDetail> values=(ArrayList<EventDetail>) postSnapshot.getValue();

                    EventDetail eventDetail=postSnapshot.getValue(EventDetail.class);
                    //words.add(new Word(post1.Title, post1.Description, post1.url,post1.newtag));
                    //adapter.notifyDataSetChanged();
                    allevent.add(eventDetail);
                    Log.v("Capital",""+allevent.size());
                    EventListFragment_RecyclerView.setAdapter(new RecyclerViewAdapter(allevent,getActivity()));
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

    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}
