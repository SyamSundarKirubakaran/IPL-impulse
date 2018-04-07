package com.bugscript.iplimpulse.groups;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.MatchDetails;
import com.bugscript.iplimpulse.R;
import com.bugscript.iplimpulse.fragments.GroupFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{

    private Context mContext;

    public GroupAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.group_name.setText(GroupFragment.val_name[position]);
        holder.pts.setText(GroupFragment.val_points[position]);
    }

    @Override
    public int getItemCount() {
        return GroupFragment.pp[0];
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.main_text_name_group) TextView group_name;
        @BindView(R.id.textView38) TextView pts;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
