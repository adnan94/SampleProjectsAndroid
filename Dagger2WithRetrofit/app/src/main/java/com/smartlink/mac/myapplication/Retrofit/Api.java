package com.smartlink.mac.myapplication.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("marvel")
    Call<List<Pojoclass>> getHeroes();
}
