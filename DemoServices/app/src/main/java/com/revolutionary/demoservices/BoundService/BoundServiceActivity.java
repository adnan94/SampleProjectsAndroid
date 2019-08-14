package com.revolutionary.demoservices.BoundService;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.revolutionary.demoservices.R;

public class BoundServiceActivity extends AppCompatActivity {



    // UI Components
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private Button mButton;

    private static final String TAG = "BoundServiceActivity";
    // Vars
    private MyService mService;
    private MainActivityViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        mProgressBar = findViewById(R.id.progresss_bar);
        mTextView = findViewById(R.id.text_view);
        mButton = findViewById(R.id.toggle_updates);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setObservers();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleUpdates();
            }
        });
    }


    private void toggleUpdates(){
        if(mService != null){
            if(mService.getProgress() == mService.getMaxValue()){
                mService.resetTask();
                mButton.setText("Start");
            }
            else{
                if(mService.getIsPaused()){
                    mService.unPausePretendLongRunningTask();
                    mViewModel.setIsProgressBarUpdating(true);
                }
                else{
                    mService.pausePretendLongRunningTask();
                    mViewModel.setIsProgressBarUpdating(false);
                }
            }

        }
    }

    private void setObservers(){
        mViewModel.getBinder().observe(this, new Observer<MyService.MyBinder>() {
            @Override
            public void onChanged(@Nullable MyService.MyBinder myBinder) {
                if(myBinder == null){
                    Log.d(TAG, "onChanged: unbound from service");
                }
                else{
                    Log.d(TAG, "onChanged: bound to service.");
                    mService = myBinder.getService();
                }
            }
        });

        mViewModel.isProgressBarUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean aBoolean) {
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        if(mViewModel.isProgressBarUpdating().getValue()){
                            if(mViewModel.getBinder().getValue() != null){ // meaning the service is bound
                                if(mService.getProgress() == mService.getMaxValue()){
                                    mViewModel.setIsProgressBarUpdating(false);
                                }
                                mProgressBar.setProgress(mService.getProgress());
                                mProgressBar.setMax(mService.getMaxValue());
                                String progress =
                                        String.valueOf(100 * mService.getProgress() / mService.getMaxValue()) + "%";
                                mTextView.setText(progress);
                            }
                            handler.postDelayed(this, 100);
                        }
                        else{
                            handler.removeCallbacks(this);
                        }
                    }
                };

                // control what the button shows
                if(aBoolean){
                    mButton.setText("Pause");
                    handler.postDelayed(runnable, 100);

                }
                else{
                    if(mService.getProgress() == mService.getMaxValue()){
                        mButton.setText("Restart");
                        finish();
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    }
                    else{
                        mButton.setText("Start");
                    }
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        startService();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mViewModel.getBinder() != null){
            unbindService(mViewModel.getServiceConnection());
        }
    }

    private void startService(){
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);

        bindService();
    }

    private void bindService(){
        Intent serviceBindIntent =  new Intent(this, MyService.class);
        bindService(serviceBindIntent, mViewModel.getServiceConnection(), Context.BIND_AUTO_CREATE);
    }


}














