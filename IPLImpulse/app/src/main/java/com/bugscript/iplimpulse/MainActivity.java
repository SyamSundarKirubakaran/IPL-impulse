package com.bugscript.iplimpulse;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bugscript.iplimpulse.bet.BetActivity;
import com.bugscript.iplimpulse.fragments.HistoryFragment;
import com.bugscript.iplimpulse.fragments.LeaderBoardsFragment;
import com.bugscript.iplimpulse.fragments.ProfileFragment;
import com.bugscript.iplimpulse.fragments.RulesFragment;
import com.bugscript.iplimpulse.fragments.ScheduleFragment;
import com.bugscript.iplimpulse.fragments.UpcomingFragment;
import com.bugscript.iplimpulse.groups.GroupsActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public static String[] time_schedule={
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "16:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)",
            "20:00 (D/N)"
    };

    private FirebaseAuth mAuth;
    public static FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private DatabaseReference d_stadium;
    private DatabaseReference d_coming;
    private DatabaseReference d_bet_on;
    private DatabaseReference d_team1;
    private DatabaseReference d_team2;
    private DatabaseReference ar_img_1,ar_img_2,arVal1,arVal2,user_name_reference, support_team_reference, points_reference;
    private DatabaseReference avg_vote,page_hits, check, win;
    public static String ar_img_1_string = "nothing", ar_img_2_string = "nothing", ar_val_1_str, ar_val_2_str;
    public static String current_support_team,current_points,current_user_name;
    public static String t1,t2;
    public static String check_str,win_str;
    public static String avg_votes_str;
    public static String page_hits_str;
    public static String current_stadium="fetching..";
    public static String current_upcoming="fetching..";
    public static String current_bet_on="fetching..";
    private ProgressDialog progress;

    public static int flag_1=0;
    public static int flag_2=0;
    public static int flag_3=0;
    public static int flag_4=0;
    public static boolean check_flag=false,win_flag=false, current_flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigation = (NavigationView) findViewById(R.id.nav_view);
        final View headerView = navigation.getHeaderView(0);
        final TextView navUsername = (TextView) headerView.findViewById(R.id.text_name_preview);

        fragmentManager=getFragmentManager();
        progress=new ProgressDialog(this);
        progress.setMessage("Fetching..");
        progress.show();

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Up coming");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
//        PrimaryWrite primaryWrite=new PrimaryWrite(currentUser.getUid(),currentUser.getPhoneNumber(),"NA","NA","1000","0");
//        databaseReference.child("user").child(currentUser.getUid()).setValue(primaryWrite);

        SharedPreferences sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
        databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("user_name").setValue(sharedPreferences.getString("name_int",currentUser.getUid()));
        databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("support_team").setValue(sharedPreferences.getString("team_str","CSK"));
        databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("bet").setValue(sharedPreferences.getString("bet_str","0"));
        databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("bet_team").setValue(sharedPreferences.getString("bet_team_str","NA"));


//        Toast.makeText(MainActivity.this,currentUser.getPhoneNumber(),Toast.LENGTH_LONG).show();
        d_stadium= FirebaseDatabase.getInstance().getReference("stadium");

//        current_stadium = drf.getDatabase().toString();
//        Toast.makeText(MainActivity.this,current_stadium+"",Toast.LENGTH_LONG).show();

        d_stadium.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_stadium = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,current_stadium+"",Toast.LENGTH_LONG).show();
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        d_coming = FirebaseDatabase.getInstance().getReference("upcoming");
        d_coming.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_upcoming = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,current_upcoming+"",Toast.LENGTH_LONG).show();
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        d_bet_on = FirebaseDatabase.getInstance().getReference("user/"+currentUser.getUid()+"/bet_team");
        d_bet_on.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_bet_on = dataSnapshot.getValue(String.class);
                current_flag=true;
//                Toast.makeText(MainActivity.this,current_bet_on+"",Toast.LENGTH_LONG).show();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_frame,new UpcomingFragment())
                        .commitAllowingStateLoss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        d_team1 = FirebaseDatabase.getInstance().getReference("team_1");
        d_team1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                t1 = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,t1+"",Toast.LENGTH_LONG).show();
                flag_1=1;
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        d_team2 = FirebaseDatabase.getInstance().getReference("team_2");
        d_team2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                t2 = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,t2+"",Toast.LENGTH_LONG).show();
                flag_2=1;
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ar_img_1 = FirebaseDatabase.getInstance().getReference("arrow_1");
        ar_img_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 ar_img_1_string= dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,ar_img_1_string+"",Toast.LENGTH_LONG).show();
                flag_3=1;
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ar_img_2 = FirebaseDatabase.getInstance().getReference("arrow_2");
        ar_img_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ar_img_2_string = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,ar_img_2_string+"",Toast.LENGTH_LONG).show();
                flag_4=1;
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        arVal1 = FirebaseDatabase.getInstance().getReference("arrow_val_1");
        arVal1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ar_val_1_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,ar_val_1_str+"",Toast.LENGTH_LONG).show();
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        arVal2 = FirebaseDatabase.getInstance().getReference("arrow_val_2");
        arVal2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ar_val_2_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,ar_val_2_str+"",Toast.LENGTH_LONG).show();
                main_replace();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        user_name_reference = FirebaseDatabase.getInstance().getReference("user/"+currentUser.getUid()+"/user_name");
        user_name_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_user_name = dataSnapshot.getValue(String.class);
                navUsername.setText(current_user_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        support_team_reference = FirebaseDatabase.getInstance().getReference("user/"+currentUser.getUid()+"/support_team");
        support_team_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_support_team = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,current_bet_on+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        points_reference = FirebaseDatabase.getInstance().getReference("user/"+currentUser.getUid()+"/points");
        points_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current_points = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,current_bet_on+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        avg_vote = FirebaseDatabase.getInstance().getReference("schedule/avg_vote");
        avg_vote.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                avg_votes_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,avg_votes_str+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        page_hits = FirebaseDatabase.getInstance().getReference("schedule/page_hits");
        page_hits.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                page_hits_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,page_hits+"",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        check = FirebaseDatabase.getInstance().getReference("enable/check");
        check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                check_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,page_hits+"",Toast.LENGTH_LONG).show();
                check_flag=true;
                if(check_flag && win_flag && current_flag){
                    UpdatePoints();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        win = FirebaseDatabase.getInstance().getReference("schedule/win");
        win.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                win_str = dataSnapshot.getValue(String.class);
//                Toast.makeText(MainActivity.this,page_hits+"",Toast.LENGTH_LONG).show();
                win_flag=true;
                if(check_flag && win_flag && current_flag){
                    UpdatePoints();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        TextView navPhone = (TextView) headerView.findViewById(R.id.textView);
        navPhone.setText(currentUser.getPhoneNumber());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Make your bet for today", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(MainActivity.this, BetActivity.class);
                                startActivity(i);
                            }
                        }).show();
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

    private void UpdatePoints() {
//        if(check_str.equals("1") && win_str.equals(current_bet_on)){
            databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("points").setValue("1080");
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to quit?");
            builder.setCancelable(true);
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
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
            Intent i =new Intent(MainActivity.this,UserInfo.class);
            startActivity(i);
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
                    .commitAllowingStateLoss();
        } else if (id == R.id.nav_schedule) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Schedule");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new ScheduleFragment())
                    .commitAllowingStateLoss();
        } else if (id == R.id.nav_groups) {
            Intent i = new Intent(MainActivity.this, GroupsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_pro) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Profile");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new ProfileFragment())
                    .commitAllowingStateLoss();
        } else if (id == R.id.leader) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Fan Graph");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new LeaderBoardsFragment())
                    .commitAllowingStateLoss();
        } else if (id == R.id.history) {
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Betting History");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new HistoryFragment())
                    .commitAllowingStateLoss();
        } else if (id == R.id.rule){
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Rule Book");
            fragmentManager.beginTransaction()
                    .replace(R.id.main_frame, new RulesFragment())
                    .commitAllowingStateLoss();
        }
//        Toast.makeText(MainActivity.this,currentUser.getUid()+"",Toast.LENGTH_LONG).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void main_replace(){
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame,new UpcomingFragment())
                .commitAllowingStateLoss();
        progress.dismiss();
    }
}
