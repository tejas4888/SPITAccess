package com.example.tejas.spitaccess2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejas.spitaccess2.R;

import java.util.ArrayList;

/**
 * Created by Tejas on 09-07-2017.
 */

public class CommitteeMemberRecyclerAdapter extends RecyclerView.Adapter<CommitteeMemberRecyclerAdapter.CommitteeMemberViewHolder> {

    ArrayList<CommitteeMember> details=new ArrayList<CommitteeMember>();
    Context context;

    public CommitteeMemberRecyclerAdapter(ArrayList<CommitteeMember> details,Context context){
        this.details=details;
        this.context=context;
    }


    @Override
    public CommitteeMemberRecyclerAdapter.CommitteeMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.committeemember_item, parent, false);
        return new CommitteeMemberRecyclerAdapter.CommitteeMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommitteeMemberRecyclerAdapter.CommitteeMemberViewHolder holder, final int position) {
        holder.name.setText(details.get(position).memberName);
        holder.designation.setText(details.get(position).memberDesignation);

        if (details.get(position).emailid!=null)
        {
            holder.email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    intent.setData(Uri.parse("mailto:" + details.get(position).emailid));

                    try {
                        context.startActivity(Intent.createChooser(intent, "Send Email via:"));
                    } catch (Exception e) {
                        Toast.makeText(context, "No email client installed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            holder.email.setVisibility(View.INVISIBLE);
        }

        if (details.get(position).phonenumber!=null)
        {
            holder.contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME,details.get(position).memberName );
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,details.get(position).phonenumber);
                    context.startActivity(intent);
                }
            });
        }else {
            holder.contact.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    static class CommitteeMemberViewHolder extends RecyclerView.ViewHolder {

        TextView name,designation;
        ImageView email,contact;
        public CommitteeMemberViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.CommitteeMember_name);
            designation=(TextView)itemView.findViewById(R.id.CommitteeMember_designation);
            email=(ImageView)itemView.findViewById(R.id.CommitteeMember_email);
            contact=(ImageView)itemView.findViewById(R.id.CommitteeMember_contact);
        }
    }
}
