package net.simplifiedcoding.androidpaginglibrary;

import android.app.Application;

public class App extends Application {

    public static App mInstance;
    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();
    }

    public static App getInstance(){
        return mInstance;
    }

}
