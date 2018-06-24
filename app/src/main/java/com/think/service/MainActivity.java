package com.think.service;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.DialogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    protected Button btn1;
    protected Button btn2;
    protected Button btn3;
    protected Button btn4;
    protected TextView currTv;
    protected TextView currThreadTv;
    String title = "服务的相关操作!";
    String[] items = {"0开启服务1",
            "1停止服务1",
            "2绑定服务1",
            "3解除绑定服务1",
            "4绑定服务2"};
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemonstrateUtil.showLogResult("onServiceConnected");
            MyService1.MyBinder binder = (MyService1.MyBinder) service;
            binder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            DemonstrateUtil.showLogResult("onServiceDisconnected");
        }
    };

    private ServiceConnection conn1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemonstrateUtil.showLogResult("onService2Connected");
            MyService2.MineBinder binder = (MyService2.MineBinder) service;
            binder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            DemonstrateUtil.showLogResult("onService2Disconnected!");
        }
    };

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case 0:
                    startService(new Intent(MainActivity.this, MyService1.class));
                    break;
                case 1:
                    stopService(new Intent(MainActivity.this, MyService1.class));
                    break;
                case 2:
                    MainActivity.this.bindService(new Intent(MainActivity.this, MyService1.class), conn, BIND_AUTO_CREATE);
                    break;
                case 3:
                    MainActivity.this.unbindService(conn);
                    break;
                case 4:
                    MainActivity.this.bindService(new Intent(MainActivity.this, MyService2.class), conn1, BIND_AUTO_CREATE);
                    break;

            }
        }
    };
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            DialogUtil.showListDialog(this, title, items, listener);
        } else if (view.getId() == R.id.btn2) {
            show2();
        } else if ((view.getId() == R.id.btn3)) {
            show3();
        } else if (view.getId() == R.id.btn4) {
            show4();
        }
    }

    private void show4() {
        Intent startIntent = new Intent(this, ServiceIntent1Activity.class);
        startActivity(startIntent);
    }

    private void show3() {
        Intent intent = new Intent(this, MyIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", "当前值：" + i++);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void show2() {
        DialogUtil.showListDialog(this, "service的播放操作!", new String[]{"0播放音乐", "1暂停音乐", "2停止音乐"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        playMusic();
                        break;
                    case 1:
                        pauseMusic();
                        break;
                    case 2:
                        stopMusic();
                        break;
                }
            }
        });
    }

    private void stopMusic() {
        Intent intent = new Intent(this, ServiceMusic.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", ServiceMusic.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
        //或者是直接如下调用
        //Intent intent = new Intent(this, MyService.class);
        //stopService(intent);
    }

    private void pauseMusic() {
        Intent intent = new Intent(this, ServiceMusic.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", ServiceMusic.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void playMusic() {
        Intent intent = new Intent(this, ServiceMusic.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", ServiceMusic.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }


    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(MainActivity.this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setText("使用Service播放音乐!");
        btn2.setOnClickListener(MainActivity.this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(MainActivity.this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(MainActivity.this);

    }


}
