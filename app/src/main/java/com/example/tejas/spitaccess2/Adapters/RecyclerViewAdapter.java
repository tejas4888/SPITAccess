package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tejas.spitaccess2.EventDetailsActivity;
import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 27-06-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<EventDetail> values;
    Context context;

    public RecyclerViewAdapter(ArrayList<EventDetail> values, Context context){
        this.values=values;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eventlist_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(values.get(position).Title);
        holder.shortdescription.setText(values.get(position).ShortDescription);
        //holder.image.setImageResource(R.drawable.fanta);
        Glide.with(context).load(values.get(position).Url).into(holder.image);

        if (TextUtils.equals(values.get(position).NewText,"0")){
            holder.newtext.setVisibility(View.INVISIBLE);
        }

        //Log.v("Details",values.get(position).Description);

        if (values.get(position).Description!=null)
        {
            Log.v("Entered","Title:"+values.get(position).Title);
        }

        holder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Entered","yeah");
                Intent intent=new Intent(context,EventDetailsActivity.class);
                intent.putExtra("Title",values.get(position).Title);
                intent.putExtra("Url",values.get(position).Url);

                intent.putExtra("Description",values.get(position).Description);
                intent.putExtra("Organizers",values.get(position).Organizers);
                intent.putExtra("OrganizersContact",values.get(position).OrganizersContact);
                intent.putExtra("Venue",values.get(position).Venue);
                intent.putExtra("Registration",values.get(position).Registration);

                intent.putExtra("Themecolor1",values.get(position).Themecolor1);
                intent.putExtra("Themecolor2",values.get(position).Themecolor2);

                //Below fields may be null
                intent.putExtra("Committee",values.get(position).Committee);
                intent.putExtra("Prizes",values.get(position).Prizes);
                intent.putExtra("Winners",values.get(position).Winners);
                intent.putExtra("RegistrationLink",values.get(position).RegistrationLink);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        //return values.size();
        return values.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,newtext,shortdescription;
        private ImageView image;
        CardView carditem;

        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.eventlistitem_eventTitle);
            shortdescription = (TextView) itemView.findViewById(R.id.eventlistitem_shortdescription);

            image= (ImageView)itemView.findViewById(R.id.eventlistitem_eventImage);
            newtext=(TextView)itemView.findViewById(R.id.eventlistitem_newtag);
            carditem=(CardView)itemView.findViewById(R.id.eventlistitem_cardcontainer);


        }
    }

}
