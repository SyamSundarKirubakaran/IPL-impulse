package com.bugscript.iplimpulse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    }
}
