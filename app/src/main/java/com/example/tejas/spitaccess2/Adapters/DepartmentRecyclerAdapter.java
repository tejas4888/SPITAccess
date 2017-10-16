package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 01-07-2017.
 */

public class DepartmentRecyclerAdapter extends RecyclerView.Adapter<DepartmentRecyclerAdapter.ViewHolder2> {

    ArrayList<DepartmentDetail> departmentDetails;
    Context context;

    public DepartmentRecyclerAdapter(ArrayList<DepartmentDetail> details,Context mcontext)
    {
        this.departmentDetails=details;
        this.context=mcontext;
    }

    @Override
    public DepartmentRecyclerAdapter.ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.departmentinfo_item, parent, false);
        return new DepartmentRecyclerAdapter.ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(DepartmentRecyclerAdapter.ViewHolder2 holder, final int position) {

        int color=Color.parseColor(departmentDetails.get(position).dept_theme);
        holder.seperator.setBackgroundColor(color);
        holder.mailimage.setColorFilter(color);

        holder.title.setText(departmentDetails.get(position).dept_title);
        Log.v("title",departmentDetails.get(position).dept_title);
        holder.programs.setText(departmentDetails.get(position).dept_programs);

        if (departmentDetails.get(position).dept_intake!=null)
        {

            holder.intake.setText(departmentDetails.get(position).dept_intake);
        }else
        {
            Log.v("ash","here");
            holder.title.setText("");
        }

        holder.intake.setText(departmentDetails.get(position).dept_intake);
        holder.hod.setText(departmentDetails.get(position).dept_hod);

        if (departmentDetails.get(position).dept_hodemailaddress==null)
        {
            ((ViewManager)holder.mailimage.getParent()).removeView(holder.mailimage);

        }else {
            holder.mailimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    intent.setData(Uri.parse("mailto:" + departmentDetails.get(position).dept_hodemailaddress));

                    try {
                        context.startActivity(Intent.createChooser(intent, "Send Email via:"));
                    } catch (Exception e) {
                        Toast.makeText(context, "No email client installed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        holder.viewwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(departmentDetails.get(position).dept_websiteurl));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return departmentDetails.size();
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView title,programs,intake,hod;
        ImageView mailimage;
        Button viewwebsite;
        View seperator;

        public ViewHolder2(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.DepartmentInfo_Title);
            programs=(TextView)itemView.findViewById(R.id.DepartmentInfo_Programs);
            intake=(TextView)itemView.findViewById(R.id.DepartmentInfo_Intake);
            hod=(TextView)itemView.findViewById(R.id.DepartmentInfo_HOD);

            mailimage=(ImageView)itemView.findViewById(R.id.DepartmentInfo_HODEmailImage);
            viewwebsite=(Button)itemView.findViewById(R.id.DepartmentInfo_WebsiteButton);

            seperator=(View)itemView.findViewById(R.id.DepartmentInfo_ViewLine);

        }
    }
}
