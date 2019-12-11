package com.revolutionary.fresco;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainApp extends Application {

     @Override
        public void onCreate() {
            super.onCreate();
            //Initializing
            Fresco.initialize(this);
        }

    }

