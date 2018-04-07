package com.bugscript.iplimpulse.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderBoardsFragment extends Fragment{

    @BindView(R.id.piechart) PieChart pieChart;

    View mView;
    private DatabaseReference d_csk,d_kkr,d_dd,d_kxip,d_mi,d_rcb,d_rr,d_srh;
    private static int csk,kkr,dd,kxip,mi,rcb,rr,srh;
    private static int sum=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.leader_boards,container,false);
        ButterKnife.bind(this,mView);

        d_csk = FirebaseDatabase.getInstance().getReference("leader_board/csk");
        d_csk.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 csk= dataSnapshot.getValue(Integer.class);
                 sum+=1;
                 if(sum >= 8)
                     initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
        d_kkr = FirebaseDatabase.getInstance().getReference("leader_board/kkr");
        d_kkr.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kkr = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_dd = FirebaseDatabase.getInstance().getReference("leader_board/dd");
        d_dd.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dd = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_kxip = FirebaseDatabase.getInstance().getReference("leader_board/kxip");
        d_kxip.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kxip = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_mi = FirebaseDatabase.getInstance().getReference("leader_board/mi");
        d_mi.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mi = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_rcb = FirebaseDatabase.getInstance().getReference("leader_board/rcb");
        d_rcb.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rcb = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_rr = FirebaseDatabase.getInstance().getReference("leader_board/rr");
        d_rr.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rr = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        d_srh = FirebaseDatabase.getInstance().getReference("leader_board/srh");
        d_srh.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                srh = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 8)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mView;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initialize_graph(){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.9f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues=new ArrayList<>();

        yValues.add(new PieEntry(csk,"CSK"));
        yValues.add(new PieEntry(rcb,"RCB"));
        yValues.add(new PieEntry(srh,"SRH"));
        yValues.add(new PieEntry(rr,"RR"));
        yValues.add(new PieEntry(kkr,"KKR"));
        yValues.add(new PieEntry(mi,"MI"));
        yValues.add(new PieEntry(dd,"DD"));
        yValues.add(new PieEntry(kxip,"KXIP"));

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutExpo);

        PieDataSet dataSet=new PieDataSet(yValues,"Teams");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
//        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setColors(new int[]{R.color.csk,R.color.rcb,R.color.srh,R.color.rr,R.color.kkr,R.color.mi,R.color.dd,R.color.kxip},getContext());

        PieData data=new PieData(dataSet);
        data.setValueTextColor(Color.YELLOW);
        data.setValueTextSize(10f);

        pieChart.setData(data);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
