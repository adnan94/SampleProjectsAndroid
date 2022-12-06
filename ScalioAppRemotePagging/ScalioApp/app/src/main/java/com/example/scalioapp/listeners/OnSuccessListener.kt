package com.example.scalioapp.listeners

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.scalioapp.model.User

interface OnSuccessListener {
    fun onSuccess(list: LiveData<PagingData<User>>)
}