package com.example.tejas.spitaccess2.Adapters;

import android.view.View;

/**
 * Created by Tejas on 27-06-2017.
 */

public class HangoutItemAdapter {

    String title,address,menu_url,map_url,phonenumber;
    int imageID;

    public HangoutItemAdapter(String title, String address, String menu_url, String map_url,String phonenumber, int imageID)
    {
        this.title=title;
        this.address=address;
        this.menu_url=menu_url;
        this.map_url=map_url;
        this.phonenumber=phonenumber;
        this.imageID=imageID;
    }


}
