package com.example.tejas.spitaccess2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.telecom.GatewayInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class EventDetailsActivity extends AppCompatActivity {

    ImageView eventImg,organizerscall,organizerscontact;
    TextView RegistrationLinkText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);

        final Intent intent=getIntent();
        String imgurl=intent.getStringExtra("Url");
        String title=intent.getStringExtra("Title");

        int themecolor1=Color.parseColor(intent.getStringExtra("Themecolor1"));
        int themecolor2=Color.parseColor(intent.getStringExtra("Themecolor2"));

        toolbar.setTitle(title);
        collapsingToolbarLayout.setContentScrimColor(themecolor1);
        setSupportActionBar(toolbar);

        eventImg=(ImageView)findViewById(R.id.eventImg);
        Glide.with(this).load(imgurl).into(eventImg);

        assignThemeForStaticText(themecolor2);
        assignTextforDynamicText();

        organizerscall=(ImageView) findViewById(R.id.eventdetails_OrganizersCall);
        organizerscontact=(ImageView)findViewById(R.id.eventdetails_OrganizersContact);
        RegistrationLinkText=(TextView)findViewById(R.id.eventdetails_RegistrationLink);
        final String organizer_contactnumber=intent.getStringExtra("OrganizersContact");

        organizerscall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent1 = new Intent(Intent.ACTION_DIAL);
                callIntent1.setData(Uri.parse("tel:" + organizer_contactnumber));
                startActivity(callIntent1);
            }
        });

        organizerscontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callintent2 = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                callintent2.putExtra(ContactsContract.Intents.Insert.NAME, intent.getStringExtra("Organizers"));
                callintent2.putExtra(ContactsContract.Intents.Insert.PHONE, "" +organizer_contactnumber);
                startActivity(callintent2);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void assignThemeForStaticText(int theme)
    {
        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_DescriptionStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_VenueStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_PrizesStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_RegistrationStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_CommitteeStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_OrganizersStatic);
            statictext.setTextColor(theme);
        }

        {
            TextView statictext = (TextView) findViewById(R.id.eventdetails_WinnersStatic);
            statictext.setTextColor(theme);
        }
    }

    void assignTextforDynamicText()
    {
        final Intent intent=getIntent();

        if (intent.getStringExtra("Committee")==null) {
            CardView card=(CardView)findViewById(R.id.eventdetails_CommitteeCard);
            ((ViewManager)card.getParent()).removeView(card);
        } else {
            TextView textView=(TextView)findViewById(R.id.eventdetails_CommitteeText);
            textView.setText(intent.getStringExtra("Committee"));
        }

        if (intent.getStringExtra("Prizes")==null) {
            CardView card=(CardView)findViewById(R.id.eventdetails_PrizesCard);
            ((ViewManager)card.getParent()).removeView(card);
        } else {
            TextView textView=(TextView)findViewById(R.id.eventdetails_PrizesText);
            textView.setText(intent.getStringExtra("Prizes"));
        }

        if (intent.getStringExtra("Winners")==null) {
            CardView card=(CardView)findViewById(R.id.eventdetails_WinnersCard);
            ((ViewManager)card.getParent()).removeView(card);
        } else {
            TextView textView=(TextView)findViewById(R.id.eventdetails_WinnersText);
            textView.setText(intent.getStringExtra("Winners"));
        }

        if (intent.getStringExtra("RegistrationLink")==null)
        {
            TextView textView=(TextView)findViewById(R.id.eventdetails_RegistrationLink);
            ((ViewManager)textView.getParent()).removeView(textView);
        } else {
            TextView textView=(TextView)findViewById(R.id.eventdetails_RegistrationLink);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ilink = new Intent(Intent.ACTION_VIEW);
                    ilink.setData(Uri.parse(intent.getStringExtra("RegistrationLink")));
                    startActivity(ilink);
                }
            });
        }

        TextView descriptiontext = (TextView) findViewById(R.id.eventdetails_DescriptionText);
        descriptiontext.setText(intent.getStringExtra("Description"));

        TextView venuetext = (TextView) findViewById(R.id.eventdetails_VenueText);
        venuetext.setText(intent.getStringExtra("Venue"));

        TextView registrationtext = (TextView) findViewById(R.id.eventdetails_RegistrationText);
        registrationtext.setText(intent.getStringExtra("Registration"));

        TextView organizerstext = (TextView) findViewById(R.id.eventdetails_OrganizersText);
        organizerstext.setText(intent.getStringExtra("Organizers"));

    }

}
