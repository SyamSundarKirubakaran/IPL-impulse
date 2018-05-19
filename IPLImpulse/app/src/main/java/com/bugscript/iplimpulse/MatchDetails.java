package com.bugscript.iplimpulse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchDetails extends AppCompatActivity {
    @BindView(R.id.movieTitleValue)
    TextView stadium_con;
    @BindView(R.id.movieReleaseDateValue)
    TextView date_con;
    @BindView(R.id.movieVoteValue)
    TextView time_con;
    @BindView(R.id.textView20)
    TextView avg_votes_con;
    @BindView(R.id.textView22)
    TextView page_hits;

    public static int id;
    private static String s1,s2,s3,s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        id=getIntent().getExtras().getInt("pos");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(MainActivity.schedule_list_fixed[id]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        stadium_con.setText(MainActivity.stadium[id]);
        date_con.setText(MainActivity.dates[id]);
        time_con.setText(MainActivity.time_schedule[id]);
        avg_votes_con.setText(MainActivity.avg_votes_str);
        page_hits.setText(MainActivity.page_hits_str);
        s1= MainActivity.schedule_list_fixed[id];
        s2= MainActivity.stadium[id];
        s3= MainActivity.time_schedule[id];
        s4= MainActivity.dates[id];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_share){
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String shareSub = s1+" : Shared through IPL Impulse";
            String shareBody="Match: "+s1+", Dates: "+s4+", Time: "+s3+", Stadium: "+s2+".\n\n Shared via IPL Impulse\n\n Get the app on Play Store : https://play.google.com/store/apps/details?id=com.bugscript.iplimpulse ";
            i.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            i.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(i,"Share with:"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
