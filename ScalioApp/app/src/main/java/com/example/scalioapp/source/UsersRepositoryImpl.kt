package com.example.scalioapp.source

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.scalioapp.ScalioApplication
import com.example.scalioapp.data.requests.Api
import com.example.scalioapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor() : UsersRepository {

    @Inject
    lateinit var api:Api

    @WorkerThread
    override suspend fun getUsersList(query: String): LiveData<PagingData<User>> {
        return withContext(Dispatchers.IO){
            Pager(
                config = PagingConfig(
                    pageSize = 9,
                    enablePlaceholders = false,
                    initialLoadSize = 2
                ),
                pagingSourceFactory = {
                    UsersPagingSource(api, query)
                }, initialKey = 1
            ).liveData
        }
    }
}