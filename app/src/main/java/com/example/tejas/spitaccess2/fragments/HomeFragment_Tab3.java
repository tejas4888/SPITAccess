package com.example.tejas.spitaccess2.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment_Tab3 extends Fragment {

    TextView websitetext,maptext,principalemail;
    ImageView call1,call2,contact1,contact2;

    public HomeFragment_Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_fragment__tab3, container, false);
        // Inflate the layout for this fragment

        websitetext=(TextView)view.findViewById(R.id.HomeFragment_Tab3Website);
        maptext=(TextView)view.findViewById(R.id.HomeFragment_Tab3Map);
        principalemail=(TextView)view.findViewById(R.id.HomeFragment_Tab3Email);

        call1=(ImageView)view.findViewById(R.id.HomeFragment_Tab3CallImage1);
        call2=(ImageView)view.findViewById(R.id.HomeFragment_Tab3CallImage2);
        contact1=(ImageView)view.findViewById(R.id.HomeFragment_Tab3AddContactImage1);
        contact2=(ImageView)view.findViewById(R.id.HomeFragment_Tab3AddContactImage2);

        websitetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.spit.ac.in/"));
                startActivity(i);
            }
        });

        maptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mapuri=Uri.parse("geo:?q=Sardar Patel College of Engineering");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, mapuri);
                startActivity(intent);
            }
        });

        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "02226707440"));
                startActivity(callIntent);

            }
        });

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "02226287250"));
                startActivity(callIntent);
            }
        });

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, "SPIT Office1");
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "02226707440");
                startActivity(intent);

            }
        });

        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, "SPIT Office2");
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "02226287250");

                startActivity(intent);
            }
        });

        principalemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + "principal@s pit.ac.in"));

                try {
                    startActivity(Intent.createChooser(intent, "Send Email via:"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No email client installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
