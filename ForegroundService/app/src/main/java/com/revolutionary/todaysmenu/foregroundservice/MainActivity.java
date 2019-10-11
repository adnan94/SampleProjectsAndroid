package com.revolutionary.todaysmenu.foregroundservice;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);

        if (!ForegroundService.IS_SERVICE_RUNNING) {
            button.setText("Start Service");
        } else {
            button.setText("Stop Service");

        }

    }

    public void buttonClicked(View v) {
        Button button = (Button) v;
        Intent service = new Intent(MainActivity.this, ForegroundService.class);

        if (!ForegroundService.IS_SERVICE_RUNNING) {
            service.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
            ForegroundService.IS_SERVICE_RUNNING = true;
            button.setText("Stop Service");
        } else {
            service.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
            ForegroundService.IS_SERVICE_RUNNING = false;
            button.setText("Start Service");

        }
        ContextCompat.startForegroundService(MainActivity.this, service);
    }

}
