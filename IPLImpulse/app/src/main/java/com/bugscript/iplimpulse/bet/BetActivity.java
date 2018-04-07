package com.bugscript.iplimpulse.bet;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BetActivity extends AppCompatActivity {
    @BindView(R.id.spinner2) Spinner bet_point_spinner;
    @BindView(R.id.floatingActionButton2) FloatingActionButton fab2;
    @BindView(R.id.imageView9) ImageView to_1;
    @BindView(R.id.imageView10) ImageView to_2;
    @BindView(R.id.imageView11) ImageView tick_1;
    @BindView(R.id.imageView12) ImageView tick_2;

    public String[] amount = {"50","100"};
    public String selected_team_for_bet = "NA";
    private DatabaseReference databaseReference;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        ButterKnife.bind(this);
        switch (MainActivity.t1) {
            case "csk":
                to_1.setImageResource(R.drawable.csk);
                break;
            case "rcb":
                to_1.setImageResource(R.drawable.rcb);
                break;
            case "kxip":
                to_1.setImageResource(R.drawable.pun);
                break;
            case "dd":
                to_1.setImageResource(R.drawable.dd);
                break;
            case "kkr":
                to_1.setImageResource(R.drawable.kkr);
                break;
            case "rr":
                to_1.setImageResource(R.drawable.rr);
                break;
            case "mi":
                to_1.setImageResource(R.drawable.mi);
                break;
            case "srh":
                to_1.setImageResource(R.drawable.srh);
                break;
        }
        switch (MainActivity.t2) {
            case "csk":
                to_2.setImageResource(R.drawable.csk);
                break;
            case "rcb":
                to_2.setImageResource(R.drawable.rcb);
                break;
            case "kxip":
                to_2.setImageResource(R.drawable.pun);
                break;
            case "dd":
                to_2.setImageResource(R.drawable.dd);
                break;
            case "kkr":
                to_2.setImageResource(R.drawable.kkr);
                break;
            case "rr":
                to_2.setImageResource(R.drawable.rr);
                break;
            case "mi":
                to_2.setImageResource(R.drawable.mi);
                break;
            case "srh":
                to_2.setImageResource(R.drawable.srh);
                break;
        }
        to_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tick_1.setImageResource(R.drawable.green_tick);
                tick_2.setImageResource(R.drawable.red_cross);
                selected_team_for_bet= MainActivity.t1;
            }
        });
        to_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tick_2.setImageResource(R.drawable.green_tick);
                tick_1.setImageResource(R.drawable.red_cross);
                selected_team_for_bet= MainActivity.t2;
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,amount);
        bet_point_spinner.setAdapter(adapter);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure about this?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String bet_amount_sum = bet_point_spinner.getSelectedItem().toString();
                                databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("bet_team").setValue(selected_team_for_bet);
                                databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("bet").setValue(bet_amount_sum);
                                sp= getSharedPreferences("shared",MODE_PRIVATE);
                                sp.edit().putString("bet_str",bet_amount_sum).commit();
                                sp.edit().putString("bet_team_str",selected_team_for_bet).commit();
                                if(selected_team_for_bet.equals("NA"))
                                    Toast.makeText(BetActivity.this,"Seems you don't want to bet today.. Not selected any team.!",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(BetActivity.this,"Bet Confirmed.!",Toast.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }
}
