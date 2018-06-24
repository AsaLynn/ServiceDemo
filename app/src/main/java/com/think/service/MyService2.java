package com.think.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.demonstrate.DemonstrateUtil;

public class MyService2 extends Service {
    public MyService2() {

    }

    private MyService2.MineBinder binder = new MineBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        DemonstrateUtil.showLogResult("onCreate");
        //Notification notification = new Notification(R.mipmap.ic_launcher,
//                "有通知到来", System.currentTimeMillis());

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, "channelId10")
                .setContentTitle("有通知到来")
                .setContentText("这是通知的内容!")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
               // .setContentIntent(pendingIntent)
//                .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
                .build();
        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class MineBinder extends Binder {

        public void startDownload() {
            DemonstrateUtil.showLogResult("MyService2-->startDownload() executed");
        }
    }
}
