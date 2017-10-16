package com.example.tejas.spitaccess2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tejas.spitaccess2.fragments.SPTBIAboutFragment;

public class SPTBIActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

        String currentfragment=this.getSupportFragmentManager().findFragmentById(R.id.SPTBI_FrameContainer).getClass().getSimpleName();
        Log.v("BackPressed",currentfragment.getClass().getSimpleName());

        if(TextUtils.equals(currentfragment,"SPTBIAboutFragment")){
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SP-TBI");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#183B75")));
        setContentView(R.layout.activity_sptbi);

        getSupportFragmentManager().beginTransaction().replace(R.id.SPTBI_FrameContainer,new SPTBIAboutFragment()).commit();
    }
}
