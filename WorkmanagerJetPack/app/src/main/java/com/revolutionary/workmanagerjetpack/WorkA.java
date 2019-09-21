package com.revolutionary.workmanagerjetpack;

import androidx.annotation.NonNull;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkA extends Worker {

    private static final String TAG = "WorkA";


    public WorkA(@NonNull Context context,
                 @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d("TAG","Work A");
        return Result.success();
    }
}