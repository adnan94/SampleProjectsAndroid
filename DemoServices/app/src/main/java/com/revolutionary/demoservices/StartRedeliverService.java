package com.revolutionary.demoservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class StartRedeliverService extends Service {
    public StartRedeliverService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent!=null) {
            Toast.makeText(getApplicationContext(), "REDELIVER Service Started", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "REDELIVER Service Started (Null Intent)", Toast.LENGTH_LONG).show();
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "REDELIVER Service Created", Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "REDELIVER Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }


}
