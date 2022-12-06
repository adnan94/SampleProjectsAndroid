package com.example.scalioapp.source

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.scalioapp.data.db.AppDB
import com.example.scalioapp.data.requests.Api
import com.example.scalioapp.model.User
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor() : UsersRepository {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var db: AppDB

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getUsersList(query: String): LiveData<PagingData<User>> {
        val pagingSourceFactory = { db.userDao().getUsers() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = UsersRemoteMediator(query, api, db),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
    }