package com.revolutionary.demoservices;

import android.app.IntentService;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.revolutionary.demoservices.BoundService.BoundServiceActivity;
import com.revolutionary.demoservices.BoundService.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    Button sticky, notSticky, redeliverIntent, boundService, intentService, stopSticky, stopNotSticky,
            stopRedeliverIntent;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sticky = (Button) findViewById(R.id.sticky);
        notSticky = (Button) findViewById(R.id.notSticky);
        redeliverIntent = (Button) findViewById(R.id.redeliverIntent);
        boundService = (Button) findViewById(R.id.boundService);
        intentService = (Button) findViewById(R.id.intentService);
        stopSticky = (Button) findViewById(R.id.stopSticky);
        stopNotSticky = (Button) findViewById(R.id.stopNotSticky);
        stopRedeliverIntent = (Button) findViewById(R.id.stopRedeliverIntent);


        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setObservers();


        sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServiceSticky.class);
                i.putExtra("data", "Adnan Ahmed");
                startService(i);

            }
        });

        notSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServiceNotSticky.class);
                i.putExtra("data", "Adnan Ahmed");
                startService(i);
            }
        });

        redeliverIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StartRedeliverService.class);
                i.putExtra("data", "Adnan Ahmed");
                startService(i);
            }
        });

        boundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BoundServiceActivity.class);
                startActivity(i);
            }
        });

        intentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.startIntentService(MainActivity.this);
            }
        });

        stopSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServiceSticky.class);
                stopService(i);

            }
        });

        stopNotSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServiceNotSticky.class);
                stopService(i);

            }
        });

        stopRedeliverIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StartRedeliverService.class);
                stopService(i);
            }
        });


    }

    private void setObservers() {
        mViewModel.getIntentServiceMessageResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

            }
        });
    }


}
