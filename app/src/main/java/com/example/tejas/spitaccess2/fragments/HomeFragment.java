package com.example.tejas.spitaccess2.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tejas.spitaccess2.Adapters.CategoryAdapter;
import com.example.tejas.spitaccess2.Adapters.HomeCarouselUrl;
import com.example.tejas.spitaccess2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ActionBar.TabListener {

    FirebaseDatabase database2;
    static int databasechecker=0;

    public HomeFragment() {
        // Required empty public constructor
    }

    CarouselView HomeFragment_CarouselView;
    ViewPager HomeFragment_ViewPager;
    TabLayout HomeFragment_TabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        HomeFragment_CarouselView=(CarouselView)view.findViewById(R.id.HomeFragment_CarouselView);
        HomeFragment_CarouselView.setPageCount(4);

        HomeFragment_ViewPager=(ViewPager) view.findViewById(R.id.HomeFragment_ViewPager);
        HomeFragment_TabLayout=(TabLayout) view.findViewById(R.id.HomeFragment_TabLayout);

        CategoryAdapter adapter = new CategoryAdapter(getChildFragmentManager());

        // Set the adapter onto the view pager
        HomeFragment_ViewPager.setAdapter(adapter);

        HomeFragment_TabLayout.setupWithViewPager(HomeFragment_ViewPager);

        if (databasechecker==0) {
            database2 = FirebaseDatabase.getInstance();
            database2.setPersistenceEnabled(true);
            databasechecker=1;
        }
        else
        {
            database2=FirebaseDatabase.getInstance();
        }

        /*HomeFragment_CarouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {

                loadImageintoCarouselfromFirebase(position,imageView);
            }
        });*/

        HomeFragment_CarouselView.setViewListener(carouselViewListener);

        return view;
    }

    ViewListener carouselViewListener=new ViewListener() {
        @Override
        public View setViewForPosition(int position) {

            RelativeLayout HomeFragment_CarouselItemContainer=(RelativeLayout)getActivity().findViewById(R.id.HomeFragment_CarouselItemContainer);

            View carouselItemView=getActivity().getLayoutInflater().inflate(R.layout.fragment_home_carouselitem,HomeFragment_CarouselItemContainer,false);
            ImageView HomeFragment_CarouselItemImageView=(ImageView)carouselItemView.findViewById(R.id.HomeFragment_CarouselItemImageView);
            TextView HomeFragment_CarouselItemTextView=(TextView)carouselItemView.findViewById(R.id.HomeFragment_CarouselItemTextView);

            loadImageintoCarouselfromFirebase(position,HomeFragment_CarouselItemImageView,HomeFragment_CarouselItemTextView);

            return carouselItemView;
        }
    };

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public void loadImageintoCarouselfromFirebase(final int position, final ImageView imageView, final TextView textView)
    {
        DatabaseReference myRef=database2.getReference("homeCarousel");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HomeCarouselUrl homeCarouselUrl=dataSnapshot.getValue(HomeCarouselUrl.class);

                if (position==0)
                {
                    Glide.with(getActivity()).load(homeCarouselUrl.url1).into(imageView);
                    textView.setText(homeCarouselUrl.text1);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Log.v("Position=0",homeCarouselUrl.text1);
                }

                else if (position==1)
                {
                    Glide.with(getActivity()).load(homeCarouselUrl.url2).into(imageView);
                    textView.setText(homeCarouselUrl.text2);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Log.v("Position=1",homeCarouselUrl.text2);
                }

                else if (position==2)
                {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(getActivity()).load(homeCarouselUrl.url3).into(imageView);
                    textView.setText(homeCarouselUrl.text3);
                    Log.v("Position=2",homeCarouselUrl.text3);
                }

                else if (position==3)
                {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(getActivity()).load(homeCarouselUrl.url4).into(imageView);
                    textView.setText(homeCarouselUrl.text4);
                    Log.v("Position=3",homeCarouselUrl.text4);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
