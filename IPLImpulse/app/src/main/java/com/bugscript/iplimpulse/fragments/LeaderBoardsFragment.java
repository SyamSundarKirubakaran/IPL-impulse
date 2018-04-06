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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderBoardsFragment extends Fragment{

    @BindView(R.id.piechart) PieChart pieChart;

    View mView;
    String sample;
    String[] arr1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.leader_boards,container,false);
        ButterKnife.bind(this,mView);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.9f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues=new ArrayList<>();

        yValues.add(new PieEntry(34,"CSK"));
        yValues.add(new PieEntry(23,"RCB"));
        yValues.add(new PieEntry(15,"SRH"));
        yValues.add(new PieEntry(40,"RR"));
        yValues.add(new PieEntry(50,"KKR"));
        yValues.add(new PieEntry(9,"MI"));
        yValues.add(new PieEntry(33,"DD"));
        yValues.add(new PieEntry(51,"KXIP"));

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
        return mView;
    }
}
