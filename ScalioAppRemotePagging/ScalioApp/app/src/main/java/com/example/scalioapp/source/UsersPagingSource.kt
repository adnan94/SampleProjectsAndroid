package com.example.scalioapp.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scalioapp.model.User
import com.example.scalioapp.data.requests.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UsersPagingSource(
    private val apiService: Api,
    private val query: String
) : PagingSource<Int, User>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return withContext(Dispatchers.IO) {
            try {
                val pageNumber = params.key ?: 0
                val response = apiService.getUser(query, pageNumber, 9)
                val prevKey = if (pageNumber > 0) pageNumber - 1 else null
                val results = response.body()?.results
                val nextKey = if (results != null) pageNumber + 1 else null

                LoadResult.Page(
                    data = results ?: arrayListOf(),
                    prevKey = prevKey,
                    nextKey = nextKey
                )

            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
