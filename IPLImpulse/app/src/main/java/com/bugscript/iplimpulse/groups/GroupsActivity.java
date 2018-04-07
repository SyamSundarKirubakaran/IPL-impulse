package com.bugscript.iplimpulse.groups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;
import com.bugscript.iplimpulse.fragments.ScheduleAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupsActivity extends AppCompatActivity {

    @BindView(R.id.groupRecycler) RecyclerView recycler;

    private DatabaseReference group_name;
    public static String val_points[];
    public static String val_name[];
    public static int[] pp = new int[1];
    private static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        ButterKnife.bind(this);

        group_name = FirebaseDatabase.getInstance().getReference("user");
        group_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pp[0] = (int) dataSnapshot.getChildrenCount();
//                Toast.makeText(GroupsActivity.this,pp[0]+"",Toast.LENGTH_LONG).show();
                val_points = new String[pp[0]];
                val_name = new String[pp[0]];
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
//                    String key= snapshot.getKey();
//                    String value=snapshot.getValue().toString();
                    if(i<=pp[0]) {
                        val_points[i] = (String) snapshot.child("points").getValue();
                        val_name[i] = (String) snapshot.child("user_name").getValue();
//                        Toast.makeText(GroupsActivity.this, val_name + " " + val_points, Toast.LENGTH_LONG).show();
                        i += 1;
                    }
                }
                GroupAdapter groupAdapter = new GroupAdapter(GroupsActivity.this);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                recycler.setLayoutManager(staggeredGridLayoutManager);
                recycler.setAdapter(groupAdapter);
                i=0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
