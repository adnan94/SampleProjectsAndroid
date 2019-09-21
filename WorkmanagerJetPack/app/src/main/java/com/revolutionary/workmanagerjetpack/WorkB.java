package com.revolutionary.workmanagerjetpack;

import androidx.annotation.NonNull;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkB extends Worker {

    private static final String TAG = "WorkB";


    public WorkB(@NonNull Context context,
                 @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.d("TAG","Work B");
        return Result.success();
    }

}