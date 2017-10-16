package com.example.tejas.spitaccess2.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tejas.spitaccess2.Adapters.AnnouncementItem;
import com.example.tejas.spitaccess2.Adapters.AnnouncementsRecyclerAdapter;
import com.example.tejas.spitaccess2.Adapters.HttpHandler;
import com.example.tejas.spitaccess2.HomeScreenActivity;
import com.example.tejas.spitaccess2.MainActivity;
import com.example.tejas.spitaccess2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Announcement2Fragment extends Fragment {

    private String TAG = HomeScreenActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    //private ListView lv;
    RecyclerView recyclerView;
    LinearLayout NoInternetLayout;

    // URL to get contacts JSON
    private static String url = "https://multiplied-bins.000webhostapp.com/announcementScript.php";

    ArrayList<AnnouncementItem> announcementObjects;

    public Announcement2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_announcement2, container, false);

        announcementObjects=new ArrayList<AnnouncementItem>();

        NoInternetLayout=(LinearLayout)view.findViewById(R.id.Announcement2Fragment_NoInternetLayout);
        recyclerView=(RecyclerView)view.findViewById(R.id.Announcement2Fragment_RecyclerContainer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //lv = (ListView) findViewById(R.id.list);
        new GetContacts().execute();

        return view;
    }

    private class GetContacts extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("result");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("text");
                        String email = c.getString("url");

                        AnnouncementItem object=new AnnouncementItem(name,email);
                        announcementObjects.add(object);

                        Log.v("Elements:",object.title+object.announcementurl);


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "Couldn't get data from Server. Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getActivity(), "Please check your Internet Connection", Toast.LENGTH_LONG).show();
                        NoInternetLayout.setVisibility(View.VISIBLE);
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            recyclerView.setAdapter(new AnnouncementsRecyclerAdapter(announcementObjects,getActivity()));
        }
    }

}
