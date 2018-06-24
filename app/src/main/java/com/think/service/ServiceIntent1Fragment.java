package com.think.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by think on 2017/11/19.
 */

public class ServiceIntent1Fragment extends Fragment {

    protected View rootView;
    protected TextView currTv;
    protected TextView currThreadTv;
    protected ProgressBar progressbar;
    protected TextView progressTv;
    protected Button startBtn;
    protected Button stopBtn;

    public ServiceIntent1Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment1, null);
        return rootView;
    }








}
