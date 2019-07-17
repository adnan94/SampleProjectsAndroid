package com.smartlink.mac.demomvp.view;

import com.smartlink.mac.demomvp.model.Pojoclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Pojoclass>> getHeroes();
}
