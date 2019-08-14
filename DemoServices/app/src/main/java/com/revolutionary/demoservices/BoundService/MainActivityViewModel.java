package com.revolutionary.demoservices.BoundService;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import com.revolutionary.demoservices.Constants;
import com.revolutionary.demoservices.MyIntentService;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private MutableLiveData<Boolean> mIsProgressBarUpdating = new MutableLiveData<>();
    private MutableLiveData<MyService.MyBinder> mBinder = new MutableLiveData<>();
    private MutableLiveData<String> messageIntentService = new MutableLiveData<>();


    // Keeping this in here because it doesn't require a context
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iBinder) {
            Log.d(TAG, "ServiceConnection: connected to service.");
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mBinder.postValue(binder);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG, "ServiceConnection: disconnected from service.");
            mBinder.postValue(null);
        }
    };
    private AddressResultReceiver mResultReceiver;


    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }

    public LiveData<MyService.MyBinder> getBinder() {
        return mBinder;
    }


    public LiveData<Boolean> isProgressBarUpdating() {
        return mIsProgressBarUpdating;
    }

    public void setIsProgressBarUpdating(boolean isUpdating) {
        mIsProgressBarUpdating.postValue(isUpdating);
    }


    public LiveData<String> getIntentServiceMessageResult() {
        return messageIntentService;
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            String message = resultData.getString(Constants.RESULT_DATA_KEY);
            if (resultCode == Constants.SUCCESS_RESULT) {
                if (message != null) {
                    messageIntentService.postValue(message);
                }

            }

        }
    }

    public void startIntentService(Context c) {

        mResultReceiver = new AddressResultReceiver(new Handler());
        if (mResultReceiver != null) {
            try {
                Intent intent = new Intent(c, MyIntentService.class);
                intent.putExtra(Constants.RECEIVER, mResultReceiver);
                c.startService(intent);

            } catch (Exception e) {

            }


        }
    }


}
















