package com.revolutionary.demohorizontalui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Adnan", "Create");


//        if you want only landscape
//        if (!getResources().getBoolean(R.bool.portrait_only)) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        } else {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//        }

    }


    @Override
    protected void onDestroy() {
        Log.d("Adnan", "Destroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d("Adnan", "Resume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("Adnan", "Start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Adnan", "Stop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("Adnan", "Restart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d("Adnan","Pause");
        super.onPause();
    }
}
