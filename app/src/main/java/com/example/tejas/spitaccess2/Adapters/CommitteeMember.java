package com.example.tejas.spitaccess2.Adapters;

/**
 * Created by Tejas on 08-07-2017.
 */

public class CommitteeMember {

    public String memberName,memberDesignation;
    public String emailid,phonenumber;

    public CommitteeMember(){

    }

    public CommitteeMember(String mn,String md,String eid,String phonenumber){
        this.memberName=mn;
        this.memberDesignation=md;
        this.emailid=eid;
        this.phonenumber=phonenumber;
    }
}
