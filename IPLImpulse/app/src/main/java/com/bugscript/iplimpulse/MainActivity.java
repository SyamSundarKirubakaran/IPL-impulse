package com.bugscript.iplimpulse;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bugscript.iplimpulse.fragments.ProfileFragment;
import com.bugscript.iplimpulse.fragments.ScheduleFragment;
import com.bugscript.iplimpulse.fragments.UpcomingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    public static String[] schedule_list_fixed={
            "MI V CSK",
            "KXIP V DD",
            "KKR V RCB",
            "SRH V RR",
            "CSK V KKR",
            "RR V DD",
            "SRH V MI",
            "RCB V KXIP",
            "MI V DD",
            "KKR V SRH",
            "RCB V RR",
            "KXIP V CSK",
            "KKR V DD",
            "MI V RCB",
            "RR V KKR",
            "KXIP V SRH",
            "CSK V RR",
            "KKR V KXIP",
            "RCB V DD",
            "SRH V CSK",
            "RR V MI",
            "DD V KXIP",
            "MI V SRH",
            "RCB V CSK",
            "SRH V KXIP",
            "DD V KKR",
            "CSK V MI",
            "RR V SRH",
            "RCB V KKR",
            "CSK V DD",
            "RCB V MI",
            "DD V RR",
            "KKR V CSK",
            "KXIP V MI",
            "CSK V RCB",
            "SRH V DD",
            "MI V KKR",
            "KXIP V RR",
            "SRH V RCB",
            "RR V KXIP",
            "KKR V MI",
            "DD V SRH",
            "RR V CSK",
            "KXIP V KKR",
            "DD V RCB",
            "CSK V SRH",
            "MI V RR",
            "KXIP V RCB",
            "KKR V RR",
            "MI V KXIP",
            "RCB V SRH",
            "DD V CSK",
            "RR V RCB",
            "SRH V KKR",
            "DD V MI",
            "CSK V KXIP",
            "QUALIFIER I",
            "ELIMINATOR",
            "QUALIFIER II",
            "FINALS"
    };
    public static String[] dates ={
            "7 April",
            "8 April",
            "8 April",
            "9 April",
            "10 April",
            "11 April",
            "12 April",
            "13 April",
            "14 April",
            "14 April",
            "15 April",
            "15 April",
            "16 April",
            "17 April",
            "18 April",
            "19 April",
            "20 April",
            "21 April",
            "21 April",
            "22 April",
            "22 April",
            "23 April",
            "24 April",
            "25 April",
            "26 April",
            "27 April",
            "28 April",
            "29 April",
            "29 April",
            "30 April",
            "1 May",
            "2 May",
            "3 May",
            "4 May",
            "5 May",
            "5 May",
            "6 May",
            "6 May",
            "7 May",
            "8 May",
            "9 May",
            "10 May",
            "11 May",
            "12 May",
            "12 May",
            "13 May",
            "13 May",
            "14 May",
            "15 May",
            "16 May",
            "17 May",
            "18 May",
            "19 May",
            "19 May",
            "20 May",
            "20 May",
            "22 May",
            "23 May",
            "25 May",
            "27 May",
    };

    public static String[] stadium={
            "Wankhede Stadium, Mumbai",
            "Punjab Cricket Association Stadium, Mohali, Chandigarh",
            "Eden Gardens, Kolkata",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Sawai Mansingh Stadium, Jaipur",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "M. Chinnaswamy Stadium, Bangalore",
            "Wankhede Stadium, Mumbai",
            "Eden Gardens, Kolkata",
            "M. Chinnaswamy Stadium, Bangalore",
            "Punjab Cricket Association Stadium, Mohali, Chandigarh",
            "Eden Gardens, Kolkata",
            "Wankhede Stadium, Mumbai",
            "Sawai Mansingh Stadium, Jaipur",
            "Punjab Cricket Association Stadium, Mohali, Chandigarh",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Eden Gardens, Kolkata",
            "M. Chinnaswamy Stadium, Bangalore",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "Sawai Mansingh Stadium, Jaipur",
            "Feroz Shah Kotla, Delhi",
            "Wankhede Stadium, Mumbai",
            "M. Chinnaswamy Stadium, Bangalore",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "Feroz Shah Kotla, Delhi",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Sawai Mansingh Stadium, Jaipur",
            "M. Chinnaswamy Stadium, Bangalore",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "M. Chinnaswamy Stadium, Bangalore",
            "Feroz Shah Kotla, Delhi",
            "Eden Gardens, Kolkata",
            "Holkar Cricket Stadium, Indore",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "Wankhede Stadium, Mumbai",
            "Holkar Cricket Stadium, Indore",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "Sawai Mansingh Stadium, Jaipur",
            "Eden Gardens, Kolkata",
            "Feroz Shah Kotla, Delhi",
            "Sawai Mansingh Stadium, Jaipur",
            "Holkar Cricket Stadium, Indore",
            "Feroz Shah Kotla, Delhi",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Wankhede Stadium, Mumbai",
            "Holkar Cricket Stadium, Indore",
            "Eden Gardens, Kolkata",
            "Wankhede Stadium, Mumbai",
            "M. Chinnaswamy Stadium, Bangalore",
            "Feroz Shah Kotla, Delhi",
            "Sawai Mansingh Stadium, Jaipur",
            "Rajiv Gandhi International Cricket Stadium, Hyderabad",
            "Feroz Shah Kotla, Delhi",
            "M. A. Chidambaram Stadium, Chepauk, Chennai",
            "Wankhede Stadium, Mumbai",
            "Wankhede Stadium, Mumbai",
            "Maharashtra Cricket Association Stadium, Pune",
            "Wankhede Stadium, Mumbai"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_frame,new UpcomingFragment())
                .commit();

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Up coming");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_up) {
            // Handle the camera action
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Up coming");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new UpcomingFragment())
                    .commit();
        } else if (id == R.id.nav_schedule) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Schedule");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new ScheduleFragment())
                    .commit();
        } else if (id == R.id.nav_groups) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Groups");

        } else if (id == R.id.nav_pro) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Profile");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new ProfileFragment())
                    .commit();
        } else if (id == R.id.leader) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Leader Boards");

        } else if (id == R.id.history) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Vote History");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
