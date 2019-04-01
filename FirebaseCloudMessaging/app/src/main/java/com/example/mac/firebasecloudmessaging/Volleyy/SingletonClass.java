package com.example.mac.firebasecloudmessaging.Volleyy;

import android.annotation.TargetApi;
import android.os.Build;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonClass {


    public static SingletonClass sInstance = null;
    RequestQueue mRequestQueue;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private SingletonClass() {
        mRequestQueue = Volley.newRequestQueue(App.getAppContext());
      }

    public static SingletonClass getInstance() {
        if (sInstance == null) {
            sInstance = new SingletonClass();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
