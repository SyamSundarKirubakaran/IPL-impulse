package com.bugscript.iplimpulse.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment{

    @BindView(R.id.gridviewRecycler) RecyclerView recyclerView;

    View mView;
    private DatabaseReference databaseReference;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.schedule,container,false);
        ButterKnife.bind(this, mView);
        ScheduleAdapter scheduleAdapter =
                new ScheduleAdapter(getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(scheduleAdapter);

        int increment_counter = Integer.parseInt(MainActivity.page_hits_str);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        increment_counter+=5;
        String sample = Integer.toString(increment_counter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("schedule").child("page_hits").setValue(sample);

        return mView;
    }

}
