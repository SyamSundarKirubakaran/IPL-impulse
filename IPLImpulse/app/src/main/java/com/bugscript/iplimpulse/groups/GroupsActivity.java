package com.bugscript.iplimpulse.groups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bugscript.iplimpulse.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Objects;

public class GroupsActivity extends AppCompatActivity {

    private DatabaseReference group_name;
    private static Iterator<DataSnapshot> g_name_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        group_name = FirebaseDatabase.getInstance().getReference("group");
        final int[] pp = new int[1];
        group_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pp[0] = (int) dataSnapshot.getChildrenCount();
                Toast.makeText(GroupsActivity.this,pp[0]+"",Toast.LENGTH_LONG).show();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
//                    String value=snapshot.getValue().toString();
                    String val = (String) snapshot.child("count").getValue();
                    Toast.makeText(GroupsActivity.this,key+" "+val,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
