package com.example.tejas.spitaccess2.Adapters;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Tejas on 08-07-2017.
 */

public class CommitteeDetails {

    public String committeeinitials,committeename,committeedescription;
    public ArrayList<CommitteeMember> committeeMembers=new ArrayList<CommitteeMember>();
    public int imageID;

    public CommitteeDetails(){

    }

    public CommitteeDetails(String initials,String name,String desc,ArrayList<CommitteeMember> member,int imageid){

        this.committeeinitials=initials;
        this.committeename=name;
        this.committeedescription=desc;
        this.committeeMembers=member;
        this.imageID=imageid;

    }

}
