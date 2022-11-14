package com.example.scalioapp.source

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.scalioapp.model.User


interface UsersRepository {

    suspend fun getUsersList(query: String) : LiveData<PagingData<User>>

}