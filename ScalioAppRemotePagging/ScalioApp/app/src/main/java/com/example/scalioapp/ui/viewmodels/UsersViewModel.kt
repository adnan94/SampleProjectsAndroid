package com.example.scalioapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.scalioapp.listeners.OnSuccessListener
import com.example.scalioapp.domain.GetUsersUseCase
import com.example.scalioapp.model.User
import com.example.scalioapp.source.UsersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(val userRepositoryImpl : UsersRepositoryImpl) : ViewModel() {


    fun getUsersList(query: String, listener: OnSuccessListener) {
        viewModelScope.launch {
            val response = getUsers(query)
            launch(Dispatchers.Main) {
                listener.onSuccess(response)
            }

        }
    }

     suspend fun getUsers(query: String): LiveData<PagingData<User>> {
        return withContext(Dispatchers.IO) {
            return@withContext GetUsersUseCase(userRepositoryImpl).invoke(query)
        }
    }
}