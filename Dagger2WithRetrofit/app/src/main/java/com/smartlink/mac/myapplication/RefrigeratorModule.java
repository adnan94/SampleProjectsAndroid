package com.smartlink.mac.myapplication;

import android.util.Log;
import android.widget.Toast;

import com.smartlink.mac.myapplication.Retrofit.Api;
import com.smartlink.mac.myapplication.Retrofit.Constants;
import com.smartlink.mac.myapplication.Retrofit.Pojoclass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RefrigeratorModule {

    public RefrigeratorModule() {
    }

    @Provides
    String provideEggs() { return "Eggs"; }

    @Provides
    Integer provideQuantity() {
        return 12;
    }


    @Provides
    Retrofit getRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }


    @Provides
    Api myMarvelsListApi(Retrofit retrofit){
        Api api = retrofit.create(Api.class);
        return api;
    }

}
