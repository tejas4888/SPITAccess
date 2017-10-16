package com.example.tejas.spitaccess2.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }

    TextView email,linkedin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //aboutcontainer.addView(aboutus);
        /*View aboutus=new AboutPage(getActivity()).isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .addGroup("Connect with us")
                .addEmail("elmehdi.sakout@gmail.com")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("medyo")
                .setDescription("This is text")
                .create();*/


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_about_us, container, false);
        email=(TextView)view.findViewById(R.id.AboutApp_email);
        linkedin=(TextView)view.findViewById(R.id.AboutApp_linkedin);

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.linkedin.com/in/tejas-chheda-678a18143"));
                getActivity().startActivity(i);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + "tejas4888@gmail.com"));

                try {
                    getActivity().startActivity(Intent.createChooser(intent, "Send Email via:"));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No email client installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
