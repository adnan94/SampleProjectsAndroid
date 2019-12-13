package com.tilismtech.kaisanapp.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @POST("UC_Sicpa.aspx")
    Call<Integer> getData(@Query("Code")String code);
}
