package com.revolutionary.workmanagerjetpack;

import androidx.annotation.NonNull;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkC extends Worker {


    private static final String TAG = "WorkC";



    public WorkC(@NonNull Context context,
                 @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d("TAG","Work C");
        return Result.success();
    }
}