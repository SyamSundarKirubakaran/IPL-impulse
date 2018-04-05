package com.bugscript.iplimpulse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MatchDetails extends AppCompatActivity {

    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id=getIntent().getExtras().getInt("pos");
        Toast.makeText(MatchDetails.this,id+"",Toast.LENGTH_LONG).show();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(MainActivity.schedule_list_fixed[id]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
