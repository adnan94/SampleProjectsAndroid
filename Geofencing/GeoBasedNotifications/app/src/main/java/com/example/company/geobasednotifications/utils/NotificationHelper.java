package com.example.company.geobasednotifications.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.company.geobasednotifications.R;
import com.example.company.geobasednotifications.view.MapsActivity;

public class NotificationHelper {

    public static void sendNotification(String title, String message) {
        Context context = App.getAppContext();


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "circle_channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notifyy(context, title, message);

        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.add)
                .setTicker("")
                .setChannelId("GeoBased")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("");

        if (notificationManager != null) {
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void notifyy(Context context, String title, String message) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MapsActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        int id = (int) Math.round(Math.random()*1000);
        String CHANNEL_ID = "my_channel_" + id++;// The id of the channel.
        CharSequence name = "channelName";// The user-visible name of the channel.

        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        Notification notification =
                new NotificationCompat.Builder(context)
                        .setTicker("GeoBased")
                        .setContentTitle(title)
                        .setContentText(message)
                        .setTicker("GeoBased")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setSmallIcon(R.drawable.add)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{500, 500})
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)

                        .setChannelId(CHANNEL_ID)
                        .build();
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
        mNotificationManager.notify(0, notification);
    }
}
