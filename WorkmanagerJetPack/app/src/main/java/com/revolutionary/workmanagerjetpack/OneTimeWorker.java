package com.revolutionary.workmanagerjetpack;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class OneTimeWorker extends Worker {

    private static final String TAG = "HelloWorld";
    public OneTimeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @Override
    public Result doWork() {

        Data data = getInputData();
        String desc =  data.getString("key");

        try {
            Thread.sleep(5000);
            displayNotification("I am your work", desc);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        Log.d("CALLING","CALLING");
        Data output = new Data.Builder()
                .putString("key", "Hii Finished Successfully")
                .build();

        return Result.success(output);
    }

    private void displayNotification(String task, String desc) {

        NotificationManager manager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("workmanager", "workmanager", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "workmanager")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(1, builder.build());

    }
}
