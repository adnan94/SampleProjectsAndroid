package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.judemanutd.autostarter.AutoStartPermissionHelper
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
//
        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            WorkManagerClass::class.java, 15, TimeUnit.MINUTES
        ).setInitialDelay(15000, TimeUnit.MILLISECONDS).addTag("WorkManagerClass")
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.DEFAULT_BACKOFF_DELAY_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "WorkManagerClass",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest
        )
//

            AutoStartPermissionHelper.getInstance().getAutoStartPermission(this)

    }
}