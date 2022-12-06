package com.example.scalioapp.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.scalioapp.data.db.AppDB
import com.example.scalioapp.data.requests.Api
import com.example.scalioapp.model.RemoteKeys
import com.example.scalioapp.model.User
import com.example.scalioapp.model.UsersResponse

@OptIn(ExperimentalPagingApi::class)
class UsersRemoteMediator(var query: String, val service: Api, val db: AppDB) :
    RemoteMediator<Int, User>() {

    private val userDao = db.userDao()
    private val remoteKeysDao = db.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = service.getUser(query = query, page = currentPage, perPage = 10)
            val user:UsersResponse? = response.body()
            val endOfPaginationReached: Boolean? = user?.results?.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached!!) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.deleteAllIUsers()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = user.results.map { user ->
                    RemoteKeys(
                        id = user.login,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                user.let {
                    userDao.insert(it.results!!)
                }

            }

           MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, User>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.login?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, User>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                remoteKeysDao.getRemoteKeys(id = user.login)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, User>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { user ->
                remoteKeysDao.getRemoteKeys(id = user.login)
            }
    }
}