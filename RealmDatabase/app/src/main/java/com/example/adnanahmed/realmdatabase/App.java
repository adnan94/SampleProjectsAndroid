package com.example.adnanahmed.realmdatabase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        Realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
//        Realm.setDefaultConfiguration(config);
        super.onCreate();
    }
}
