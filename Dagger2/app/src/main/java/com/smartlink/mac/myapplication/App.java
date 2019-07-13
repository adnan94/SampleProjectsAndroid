package com.smartlink.mac.myapplication;

import android.app.Application;

public class App extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .refrigeratorModule(new RefrigeratorModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
