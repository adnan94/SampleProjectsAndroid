package com.revolutionary.workmanagerjetpack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button chainable, preodic, oneTime;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oneTime = (Button) findViewById(R.id.oneTimeRequest);
        preodic = (Button) findViewById(R.id.preodicRequest);
        chainable = (Button) findViewById(R.id.chainableRequest);

        oneTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneTime();
            }
        });

        chainable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chainable();
            }
        });

        preodic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preodicRequest();
            }
        });


    }


    private void getOutputData(UUID id) {
        WorkManager.getInstance().getWorkInfoByIdLiveData(id)
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {

                        if (workInfo != null) {

                            if (workInfo.getState().isFinished()) {

                                Data data = workInfo.getOutputData();
                                String output = data.getString("key");
                                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();

                            }

                            String status = workInfo.getState().name();
                        }
                    }
                });

    }


    private void oneTime() {

        Data data = new Data.Builder()
                .putString("key", "hii this is workmanager one time request")
                .build();

        Constraints constraints = new Constraints.Builder()
                .build();

        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
//                        .setInitialDelay(10, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance().enqueue(request);
        getOutputData(request.getId());
    }


    private void preodicRequest() {
        Data data = new Data.Builder()
                .putString("key", "hii this is preodic workmanager one time request")
                .build();

        Constraints constraints = new Constraints.Builder()
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                PeriodicWork.class, 1, TimeUnit.MINUTES)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueue(periodicWorkRequest);


        //                        .setRequiresBatteryNotLow(true)
//                        .setRequiredNetworkType(NetworkType.CONNECTED)
//                        .setRequiresStorageNotLow(true)
    }

    private void chainable() {
        OneTimeWorkRequest oneTimeWorkRequestA = new OneTimeWorkRequest.Builder(WorkA.class).build();
        OneTimeWorkRequest oneTimeWorkRequestB = new OneTimeWorkRequest.Builder(WorkB.class).build();
        OneTimeWorkRequest oneTimeWorkRequestC = new OneTimeWorkRequest.Builder(WorkC.class).build();


        WorkManager.getInstance()
                .beginWith(oneTimeWorkRequestA)
                .then(oneTimeWorkRequestB)
                .then(oneTimeWorkRequestC)
                .enqueue();

        //        List<OneTimeWorkRequest> list = new ArrayList<>();
//        list.add(oneTimeWorkRequestA);
    }


    @Override
    protected void onDestroy() {
//        WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag("");
//        WorkManager.getInstance(getApplicationContext()).cancelUniqueWork("");
//        WorkManager.getInstance(getApplicationContext()).cancelWorkById()
        super.onDestroy();
    }
}
