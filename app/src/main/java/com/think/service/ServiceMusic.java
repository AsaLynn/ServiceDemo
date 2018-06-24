package com.think.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.demonstrate.DemonstrateUtil;

/**
 * Created by think on 2017/11/18.
 */

public class ServiceMusic extends Service{

    private MediaPlayer mediaPlayer;

    private int startId;

    public enum Control {
        PLAY, PAUSE, STOP
    }

    public ServiceMusic() {
    }

    @Override
    public void onCreate() {
        DemonstrateUtil.showLogResult("onCreate");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.losing);
            mediaPlayer.setLooping(false);
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        this.startId = startId;
        DemonstrateUtil.showLogResult(startId+"");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Control control = (Control) bundle.getSerializable("Key");
            if (control != null) {
                switch (control) {
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        DemonstrateUtil.showLogResult("onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
