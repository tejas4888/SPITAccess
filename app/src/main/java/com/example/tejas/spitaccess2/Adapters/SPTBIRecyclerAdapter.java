package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 03-07-2017.
 */

public class SPTBIRecyclerAdapter extends RecyclerView.Adapter<SPTBIRecyclerAdapter.ViewHolderCustom> {

    ArrayList<SPTBIItem> allstartups=new ArrayList<SPTBIItem>();
    Context context;

    public SPTBIRecyclerAdapter(ArrayList<SPTBIItem> allstartups,Context context){
        this.allstartups=allstartups;
        this.context=context;
    }

    @Override
    public SPTBIRecyclerAdapter.ViewHolderCustom onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sptbiincubatee_item, parent, false);
        return new SPTBIRecyclerAdapter.ViewHolderCustom(view);

    }

    @Override
    public void onBindViewHolder(SPTBIRecyclerAdapter.ViewHolderCustom holder, final int position) {
        holder.title.setText(allstartups.get(position).startupname);
        Glide.with(context).load(allstartups.get(position).imageurl).into(holder.startupimage);
        holder.description.setText(allstartups.get(position).description);

       if (allstartups.get(position).websiteurl!=null)
        {

            holder.webtext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(allstartups.get(position).websiteurl));
                    context.startActivity(i);
                }
            });
        }else{
           //If webtext is removed from parent nullPointer exception is encountered for all items in recycler

            //((ViewManager)holder.webtext.getParent()).removeView(holder.webtext);
           holder.webtext.setVisibility(View.INVISIBLE);
        }

        if (allstartups.get(position).internshipurl!=null)
        {
            holder.interntext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(allstartups.get(position).internshipurl));
                    context.startActivity(i);
                }
            });
        }else{
            //((ViewManager)holder.interntext.getParent()).removeView(holder.interntext);
            holder.interntext.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return allstartups.size();
    }

    static class ViewHolderCustom extends RecyclerView.ViewHolder {

        TextView title,description,interntext,webtext;
        ImageView startupimage;

        public ViewHolderCustom(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.SPTBI_ItemTitle);
            description=(TextView)itemView.findViewById(R.id.SPTBI_ItemDescription);
            webtext=(TextView)itemView.findViewById(R.id.SPTBI_ItemWebsite);
            interntext=(TextView)itemView.findViewById(R.id.SPTBI_ItemApplyInternship);

            startupimage=(ImageView)itemView.findViewById(R.id.SPTBI_ItemImage);

        }
    }
}
