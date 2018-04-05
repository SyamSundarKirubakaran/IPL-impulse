package com.bugscript.iplimpulse.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    private ScheduleFragment mContext;

    public ScheduleAdapter(ScheduleFragment context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.match.setText(MainActivity.schedule_list_fixed[position]);
        holder.Dates.setText(MainActivity.dates[position]);
    }

    @Override
    public int getItemCount() {
        return MainActivity.schedule_list_fixed.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textView16) TextView match;
        @BindView(R.id.textView17) TextView Dates;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
