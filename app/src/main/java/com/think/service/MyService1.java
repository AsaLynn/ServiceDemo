package com.think.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.demonstrate.DemonstrateUtil;

public class MyService1 extends Service {

    Binder myBinder = new MyBinder();
    public MyService1() {
        DemonstrateUtil.showLogResult("MyService1");
    }


    @Override
    public void onCreate() {
        DemonstrateUtil.showLogResult("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DemonstrateUtil.showLogResult("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        DemonstrateUtil.showLogResult("onDestroy");
        super.onDestroy();
    }



    @Override
    public IBinder onBind(Intent intent) {
        DemonstrateUtil.showLogResult("onBind");
       return myBinder;
    }


    class MyBinder extends Binder {

        public void startDownload() {
            DemonstrateUtil.showLogResult("startDownload() executed");
        }

    }
}
