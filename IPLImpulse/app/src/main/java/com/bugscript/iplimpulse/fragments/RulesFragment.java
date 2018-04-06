package com.bugscript.iplimpulse.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bugscript.iplimpulse.MainActivity;
import com.bugscript.iplimpulse.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RulesFragment extends Fragment{

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.rules,container,false);
        return mView;
    }
}
