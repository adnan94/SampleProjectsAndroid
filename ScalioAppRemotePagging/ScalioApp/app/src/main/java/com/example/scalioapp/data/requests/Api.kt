package com.example.scalioapp.data.requests

import com.example.scalioapp.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("users")
    suspend fun getUser(@Query("q") query: String, @Query("page") page:Int, @Query("per_page") perPage:Int): Response<UsersResponse>
}