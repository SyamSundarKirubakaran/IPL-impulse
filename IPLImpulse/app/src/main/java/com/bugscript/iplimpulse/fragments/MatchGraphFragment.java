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

import com.bugscript.iplimpulse.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchGraphFragment extends Fragment{

    @BindView(R.id.piechart_1) PieChart pieChart;

    View mView;
    private DatabaseReference dd_t1,dd_t2,dd_v1,dd_v2;
    private static int ff_v1,ff_v2;
    private static String ff_t1,ff_t2;
    private static int sum=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.match_graph_fragment,container,false);
        ButterKnife.bind(this,mView);

        dd_v1 = FirebaseDatabase.getInstance().getReference("clash_graph/v1");
        dd_v1.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 ff_v1= dataSnapshot.getValue(Integer.class);
                 sum+=1;
                 if(sum >= 4)
                     initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        
        dd_v2 = FirebaseDatabase.getInstance().getReference("clash_graph/v2");
        dd_v2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ff_v2 = dataSnapshot.getValue(Integer.class);
                sum+=1;
                if(sum >= 4)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dd_t1 = FirebaseDatabase.getInstance().getReference("clash_graph/t1");
        dd_t1.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ff_t1 = dataSnapshot.getValue(String.class);
                sum+=1;
                if(sum >= 4)
                    initialize_graph();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dd_t2 = FirebaseDatabase.getInstance().getReference("clash_graph/t2");
        dd_t2.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ff_t2 = dataSnapshot.getValue(String.class);
                sum+=1;
                if(sum >= 4)
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

        yValues.add(new PieEntry(ff_v1,ff_t1));
        yValues.add(new PieEntry(ff_v2,ff_t2));
//        yValues.add(new PieEntry(srh,"SRH"));
//        yValues.add(new PieEntry(rr,"RR"));
//        yValues.add(new PieEntry(kkr,"KKR"));
//        yValues.add(new PieEntry(mi,"MI"));
//        yValues.add(new PieEntry(dd,"DD"));
//        yValues.add(new PieEntry(kxip,"KXIP"));

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutExpo);

        PieDataSet dataSet=new PieDataSet(yValues,"Teams");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
//        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setColors(new int[]{R.color.mi,R.color.rcb},getContext());

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
