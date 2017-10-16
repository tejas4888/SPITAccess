package com.example.tejas.spitaccess2.Adapters;

/**
 * Created by Tejas on 03-07-2017.
 */

public class SPTBIItem {

    public String startupname,websiteurl,internshipurl,imageurl,description;

    public SPTBIItem(){

    }

    public SPTBIItem(String startupname,String websiteurl,String internshipurl,String imageurl,String description){

        this.startupname=startupname;
        this.description=description;
        this.imageurl=imageurl;
        this.internshipurl=internshipurl;
        this.websiteurl=websiteurl;

    }
}
