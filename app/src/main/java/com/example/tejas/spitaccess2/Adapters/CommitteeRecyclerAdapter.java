package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tejas.spitaccess2.CommitteeDetailsActivity;
import com.example.tejas.spitaccess2.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tejas on 08-07-2017.
 */

public class CommitteeRecyclerAdapter extends RecyclerView.Adapter<CommitteeRecyclerAdapter.CommitteeViewHolder> {

    ArrayList<CommitteeDetails> committeedetails;
    Context context;

    public CommitteeRecyclerAdapter(ArrayList<CommitteeDetails> committeedetails,Context context){
        this.committeedetails=committeedetails;
        this.context=context;
    }

    @Override
    public CommitteeRecyclerAdapter.CommitteeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.committeelist_item, parent, false);
        return new CommitteeRecyclerAdapter.CommitteeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CommitteeRecyclerAdapter.CommitteeViewHolder holder, final int position) {
        holder.logo.setImageResource(committeedetails.get(position).imageID);
        holder.initials.setText(committeedetails.get(position).committeeinitials);
        holder.title.setText(committeedetails.get(position).committeename);

        final CommitteeDetails object=committeedetails.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CommitteeDetailsActivity.class);

                intent.putExtra("initials",committeedetails.get(position).committeeinitials);
                intent.putExtra("title",committeedetails.get(position).committeename);
                intent.putExtra("description",committeedetails.get(position).committeedescription);

                int numberOfMembers=committeedetails.get(position).committeeMembers.size();

                intent.putExtra("NumberOfMembers",numberOfMembers);
                for (int i=0;i<=numberOfMembers-1;i++)
                {
                    String a="membername"+String.valueOf(i);
                    String b="memberdesignation"+String.valueOf(i);
                    String c="memberemail"+String.valueOf(i);
                    String d="memberphone"+String.valueOf(i);
                    intent.putExtra(a,committeedetails.get(position).committeeMembers.get(i).memberName);
                    intent.putExtra(b,committeedetails.get(position).committeeMembers.get(i).memberDesignation);
                    intent.putExtra(c,committeedetails.get(position).committeeMembers.get(i).emailid);
                    intent.putExtra(d,committeedetails.get(position).committeeMembers.get(i).phonenumber);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return committeedetails.size();
    }

    static class CommitteeViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView initials,title;
        ImageView logo;
        public CommitteeViewHolder(View itemView) {
            super(itemView);

            cardView=(CardView)itemView.findViewById(R.id.CommitteeItem_Card);
            initials=(TextView)itemView.findViewById(R.id.CommitteeItem_Initials);
            title=(TextView)itemView.findViewById(R.id.CommitteeItem_Title);
            logo=(ImageView)itemView.findViewById(R.id.CommitteeItem_Logo);

        }
    }
}
