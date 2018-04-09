package com.bugscript.iplimpulse.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;
import com.bugscript.iplimpulse.groups.GroupAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupFragment extends Fragment {

    @BindView(R.id.groupRecycler)
    RecyclerView recycler;
    @BindView(R.id.textView37)
    TextView rank_current;
    @BindView(R.id.imageView14)
    ImageView arrows_rank;

    private DatabaseReference group_name, d_rank;
    public static String val_points[];
    public static String val_name[];
    public static String rank_now;
    public static int[] pp = new int[1];
    private static int i=0;
    private ProgressDialog progress;

    View mView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.group_fragment, container, false);
        ButterKnife.bind(this, mView);


        progress=new ProgressDialog(getActivity());
        progress.setMessage("Fetching..");
        progress.show();

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
                progress.dismiss();
                GroupAdapter groupAdapter = new GroupAdapter(getActivity());
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
                recycler.setLayoutManager(staggeredGridLayoutManager);
                recycler.setAdapter(groupAdapter);
                i=0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        d_rank = FirebaseDatabase.getInstance().getReference("user/"+ MainActivity.currentUser.getUid()+"/rank");
        d_rank.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rank_now = dataSnapshot.getValue(String.class);
                int rank_now_int = Integer.parseInt(rank_now);
                if(rank_now_int%3==0){
                    arrows_rank.setImageResource(R.drawable.down_red);
                    rank_current.setTextColor(Color.RED);
                }else{
                    arrows_rank.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    rank_current.setTextColor(Color.parseColor("#4CAF50"));
                }
                rank_current.setText(rank_now);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return mView;
    }

}
