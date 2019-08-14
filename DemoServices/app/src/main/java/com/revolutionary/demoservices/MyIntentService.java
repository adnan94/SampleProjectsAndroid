package com.revolutionary.demoservices;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ResultReceiver;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    protected ResultReceiver mReceiver;


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(getApplicationContext(), "ON HANDLE Intent", Toast.LENGTH_LONG).show();

        mReceiver = intent.getParcelableExtra(Constants.RECEIVER);
        deliverResultToReceiver(Constants.SUCCESS_RESULT, "New message recieved");


    }

    private void deliverResultToReceiver(final int resultCode, final String message) {



        final HandlerThread handlerThread = new HandlerThread("background-thread");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Bundle bundle = new Bundle();
                bundle.putString(Constants.RESULT_DATA_KEY, message);
                mReceiver.send(resultCode, bundle);
                handler.removeCallbacks(this);
//                handlerThread.stop();
            }
        }, 3000);

//        try {
//            Thread.sleep(3000);
//            Bundle bundle = new Bundle();
//            bundle.putString(Constants.RESULT_DATA_KEY, message);
//            mReceiver.send(resultCode, bundle);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Destroyed Intent Serivce", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Toast.makeText(getApplicationContext(), "On Task Removed", Toast.LENGTH_LONG).show();

//        stopSelf();
    }
}
