package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 05-07-2017.
 */

public class AnnouncementsRecyclerAdapter extends RecyclerView.Adapter<AnnouncementsRecyclerAdapter.AnnouncementViewholder> {

    ArrayList<AnnouncementItem> announcements=new ArrayList<AnnouncementItem>();
    Context context;

    public AnnouncementsRecyclerAdapter(ArrayList<AnnouncementItem> announcements,Context context){
        this.announcements=announcements;
        this.context=context;
    }

    @Override
    public AnnouncementsRecyclerAdapter.AnnouncementViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.announcement_item, parent, false);
        return new AnnouncementsRecyclerAdapter.AnnouncementViewholder(view);
    }

    @Override
    public void onBindViewHolder(AnnouncementsRecyclerAdapter.AnnouncementViewholder holder, final int position) {

        holder.title.setText(announcements.get(position).title);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(announcements.get(position).announcementurl));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    static class AnnouncementViewholder extends RecyclerView.ViewHolder {

        TextView title;
        CardView cardView;
        public AnnouncementViewholder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.AnnouncementItem_Title);
            cardView=(CardView)itemView.findViewById(R.id.AnnouncementItem_CardContainer);
        }
    }
}
