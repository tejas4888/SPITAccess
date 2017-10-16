package com.example.tejas.spitaccess2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tejas.spitaccess2.fragments.AboutUsFragment;
import com.example.tejas.spitaccess2.fragments.Announcement2Fragment;
import com.example.tejas.spitaccess2.fragments.AnnouncementFragment;
import com.example.tejas.spitaccess2.fragments.CommitteeListFragment;
import com.example.tejas.spitaccess2.fragments.DepartmentFragment;
import com.example.tejas.spitaccess2.fragments.EventListFragment;
import com.example.tejas.spitaccess2.fragments.HangoutPlacesFragment;
import com.example.tejas.spitaccess2.fragments.HomeFragment;
import com.example.tejas.spitaccess2.fragments.ResourceCategoriesFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.util.Stack;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Stack<Fragment> fragmentStack;

    FragmentManager fm;
    NavigationView navigationView;
    static int backcounter=1;

    private FirebaseAuth firebaseAuth;
    TextView HomeScreen_UserEmailId;
    FrameLayout HomeScreen_FragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SPIT Access");
        setSupportActionBar(toolbar);

        fragmentStack=new Stack<Fragment>();
        fragmentStack.push(new HomeFragment());
        Log.d("fragmentStack1",""+fragmentStack.size());

        HomeScreen_FragmentContainer=(FrameLayout)findViewById(R.id.HomeScreen_FragmentContainer);

        fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new HomeFragment()).commit();

        this.getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment=getCurrentFragment();

                if (fragment==new AnnouncementFragment()){
                    Log.i("Reached herre","An");
                }
            }
        });
                //For Immersive mode on the application
        /*getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/

        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }
        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ((ViewManager)fab.getParent()).removeView(fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        View headerView = navigationView.getHeaderView(0);
        TextView HomeScreen_UserTimeGreeting=(TextView)headerView.findViewById(R.id.HomeScreen_UserTimeGreeting);
        int hours = new Time(System.currentTimeMillis()).getHours();
        Log.v("Hours","H:"+hours);
        if (hours>=8 && hours<17)
        {
            headerView.setBackgroundResource(R.drawable.morning);
            HomeScreen_UserTimeGreeting.setText("Good Day!");
        }
        else if (hours>=17 && hours<19)
        {
            headerView.setBackgroundResource(R.drawable.evening);
            HomeScreen_UserTimeGreeting.setText("Good Evening!");
        }
        else if (hours>=5 && hours<8)
        {
            headerView.setBackgroundResource(R.drawable.earlymorning);
            HomeScreen_UserTimeGreeting.setText("Good Morning!");
        }
        else
        {
            headerView.setBackgroundResource(R.drawable.night);
            HomeScreen_UserTimeGreeting.setText("Good Night!");
        }
        HomeScreen_UserEmailId = (TextView) headerView.findViewById(R.id.HomeScreen_UserEmailId);
        HomeScreen_UserEmailId.setText(user.getEmail());

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            //Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();

        } else  {
            Snackbar.make((FrameLayout)findViewById(R.id.HomeScreen_FragmentContainer),"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        //if (fragmentStack.size()>=2) {
            Log.v("fragmentStack2",""+fragmentStack.size());
            //fragmentStack.pop();
            //getSupportFragmentManager().beginTransaction().replace(R.id.HomeScreen_FragmentContainer,fragmentStack.lastElement()).commit();
            if (backcounter==2){
                backcounter=1;
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new HomeFragment()).commit();
            navigationView.getMenu().getItem(0).setChecked(true);

        }else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.HomeScreen_action_signOut) {
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(HomeScreenActivity.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_drawer_Home) {
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new HomeFragment()).commit();
            Log.v("NAV Drawer","Home");
        } else if (id == R.id.nav_drawer_Announcements){
            backcounter=2;
            fragmentStack.push(new AnnouncementFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new Announcement2Fragment()).commit();
        } else if (id == R.id.nav_drawer_Departments) {
            backcounter=2;
            fragmentStack.push(new DepartmentFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new DepartmentFragment()).commit();
            Log.v("NAV Drawer","Departments");
        } else if (id == R.id.nav_drawer_Events) {
            backcounter=2;
            fragmentStack.push(new EventListFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new EventListFragment()).commit();
            Log.v("NAV Drawer","Events");
        } else if (id == R.id.nav_drawer_Committees) {
            backcounter=2;
            fragmentStack.push(new CommitteeListFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new CommitteeListFragment()).commit();
            Log.v("NAV Drawer","Committees");
        } else if (id == R.id.nav_drawer_Hangouts) {
            backcounter=2;
            fragmentStack.push(new HangoutPlacesFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new HangoutPlacesFragment()).commit();
            Log.v("NAV Drawer","Hangouts");
        } else if (id == R.id.nav_drawer_SPTBI){
            backcounter=2;
            Log.v("NAV Drawer","SPTBI");
            Intent intent=new Intent(HomeScreenActivity.this,SPTBIActivity.class);
            startActivity(intent);
        }   else if (id == R.id.nav_drawer_AboutApp){
            backcounter=2;
            fragmentStack.push(new AboutUsFragment());
            fm.beginTransaction().replace(R.id.HomeScreen_FragmentContainer,new AboutUsFragment()).commit();
            Log.v("NAV Drawer","About App");
        }

        

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Fragment getCurrentFragment() {
        return this.getSupportFragmentManager().findFragmentById(R.id.HomeScreen_FragmentContainer);
    }
}
