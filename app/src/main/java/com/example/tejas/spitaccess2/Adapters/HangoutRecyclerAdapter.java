package com.example.tejas.spitaccess2.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 27-06-2017.
 */

public class HangoutRecyclerAdapter extends RecyclerView.Adapter<HangoutRecyclerAdapter.MyViewHolder> {

    ArrayList<HangoutItemAdapter> allplaces;
    Context context;

    public HangoutRecyclerAdapter(ArrayList<HangoutItemAdapter> allplaces, Context context) {
        this.allplaces = allplaces;
        this.context = context;
    }

    @Override
    public HangoutRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return null;

        View view = LayoutInflater.from(context).inflate(R.layout.hangoutplace_item, parent, false);
        return new HangoutRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HangoutRecyclerAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(allplaces.get(position).title);
        holder.address.setText(allplaces.get(position).address);
        holder.image.setImageResource(allplaces.get(position).imageID);

        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(allplaces.get(position).map_url));
                context.startActivity(intent);
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(allplaces.get(position).menu_url));
                context.startActivity(i);
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + allplaces.get(position).phonenumber));
                context.startActivity(callIntent);
            }
        });

        holder.addtocontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                intent.putExtra(ContactsContract.Intents.Insert.NAME, allplaces.get(position).title);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "" + allplaces.get(position).phonenumber);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allplaces.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,address,map,menu;
        ImageView image,call,addtocontact;

        MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.hangoutitem_title);
            address=(TextView)itemView.findViewById(R.id.hangoutitem_address);

            image=(ImageView)itemView.findViewById(R.id.hangoutitem_image);

            map=(TextView)itemView.findViewById(R.id.hangoutitem_map);
            menu=(TextView)itemView.findViewById(R.id.hangoutitem_menu);

            call=(ImageView)itemView.findViewById(R.id.hangoutitem_call);
            addtocontact=(ImageView)itemView.findViewById(R.id.hangoutitem_addtocontact);

        }
    }
}
