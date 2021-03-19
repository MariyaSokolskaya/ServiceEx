package com.samsung.itschool.serviceex;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.loshad);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //TODO определить канал
        NotificationChannel nChannel = new NotificationChannel("Music Channel", "Music"
                , NotificationManager.IMPORTANCE_DEFAULT);
        //TODO создать PendingIntent
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this,"Music Channel")
                .setContentTitle("Сейчас играет:")
                .setContentText("цокот копыт")
                .setColor(Color.GREEN)
                .setChannelId("Music Channel")
                .setContentIntent();

        Notification notification = nBuilder.build();
        notification.notify();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
