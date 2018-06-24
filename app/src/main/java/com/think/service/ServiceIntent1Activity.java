package com.think.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ServiceIntent1Activity extends AppCompatActivity implements View.OnClickListener{

    public static final String ACTION_TYPE_SERVICE = "ACTION_TYPE_SERVICE";
    public static final String ACTION_TYPE_THREAD = "ACTION_TYPE_THREAD";


    protected TextView currTv;
    protected TextView currThreadTv;
    protected ProgressBar progressbar;
    protected TextView progressTv;
    protected Button startBtn;
    protected Button stopBtn;
    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_service_intent1);
        initView();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_SERVICE);
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_btn) {
            startService(new Intent(this, MyIntentService1.class));
        } else if (view.getId() == R.id.stop_btn) {
            stopService(new Intent(this, MyIntentService1.class));
        }
    }

    private void initView() {
        currTv = (TextView) findViewById(R.id.curr_tv);
        currThreadTv = (TextView) findViewById(R.id.curr_thread_tv);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressTv = (TextView) findViewById(R.id.progress_tv);
        startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(ServiceIntent1Activity.this);
        stopBtn = (Button) findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(ServiceIntent1Activity.this);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_TYPE_SERVICE:
                    currTv.setText("服务状态：" + intent.getStringExtra("status"));
                    break;
                case ACTION_TYPE_THREAD:
                    int progress = intent.getIntExtra("progress", 0);
                    currThreadTv.setText("线程状态：" + intent.getStringExtra("status"));
                    progressbar.setProgress(progress);
                    progressTv.setText(progress + "%");
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}
