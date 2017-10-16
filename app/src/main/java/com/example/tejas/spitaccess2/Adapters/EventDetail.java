package com.example.tejas.spitaccess2.Adapters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tejas on 27-06-2017.
 */

public class EventDetail {

    public String Title;
    public String Url;
    public String NewText;
    public String ShortDescription,Description,Venue,Organizers,Registration,OrganizersContact;
    public String Themecolor1,Themecolor2;
    //Below are optional event entries
    public String Committee,Prizes,Winners,RegistrationLink;
    //This above name must match with the Database key name
    public Map<String, Boolean> stars = new HashMap<>();

    public EventDetail() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public EventDetail(String Title,String Url,String NewText,String sd,String desc,String venue,String organizers,String committee,String registration,String organizersContact,String themecolor1,String themecolor2,String prizes,String winners,String registrationLink) {
        this.Title=Title;
        this.Url=Url;
        this.NewText=NewText;
        this.ShortDescription=sd;
        this.Description=desc;
        this.Venue=venue;
        this.Organizers=organizers;
        this.Registration=registration;
        this.OrganizersContact=organizersContact;

        this.Themecolor1=themecolor1;
        this.Themecolor2=themecolor2;

        this.Committee=committee;
        this.Prizes=prizes;
        this.Winners=winners;
        this.RegistrationLink=registrationLink;
    }


}
