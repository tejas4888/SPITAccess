package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tejas.spitaccess2.R;
import com.example.tejas.spitaccess2.fragments.HomeFragment_Tab1;
import com.example.tejas.spitaccess2.fragments.HomeFragment_Tab2;
import com.example.tejas.spitaccess2.fragments.HomeFragment_Tab3;

/**
 * Created by Tejas on 23-06-2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    //Context mContext;
    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment_Tab1();
        }  else if (position == 1){
            return new HomeFragment_Tab2();
        }   else {
            return new HomeFragment_Tab3();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "About Us";
        }  else if (position == 1){
            return "Vision and Mission";
        }   else {
            return "Contact Us";
        }
    }
}
