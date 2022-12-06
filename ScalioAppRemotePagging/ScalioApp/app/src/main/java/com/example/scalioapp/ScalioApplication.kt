package com.example.scalioapp

import android.app.Application
import com.example.scalioapp.data.requests.Api
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class ScalioApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
    }

}