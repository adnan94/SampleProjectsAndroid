package com.revolutionary.orchardoranges.utils;

import android.app.Application;


//import com.crashlytics.android.Crashlytics;

//import io.fabric.sdk.android.Fabric;

/**
 * Created by Ali on 4/18/2017.
 */

public class App extends Application {
    private static App mInstance;
    @Override
    public void onCreate() {

        mInstance = this;
        super .onCreate();
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

}
