package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.R
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class WorkManagerClass(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private lateinit var notificationManager: NotificationManager

    override fun doWork(): Result {
        createNotification(applicationContext)
        Log.d("WorkerManager", "running " + Date())
        return Result.success();
    }


    fun createNotification(context: Context) {
        var notificationCompat = NotificationCompat.Builder(context, "DATA_BACKUP")
            .setContentTitle("worker")
            .setSmallIcon(com.example.myapplication.R.mipmap.ic_launcher)
            .setContentText("worker running")
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChanel()
        val notification: Notification = notificationCompat.build()
        notificationManager.notify(Random().nextInt() * 100, notification)
    }

    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "DATA_BACKUP",
                    "DATA_BACKUP",
                    NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }
}