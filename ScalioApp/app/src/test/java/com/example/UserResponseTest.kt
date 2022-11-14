package com.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.scalioapp.model.User
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


class UserResponseTest {

    fun getUsersList(): List<User> {
        val jsonUserResponse = JSONObject(readFromFile("/user_response.json"));
        val usersJsonArray: JSONArray = jsonUserResponse.getJSONArray("items")
        val users = arrayListOf<User>()
        for (i in 0 until usersJsonArray.length()) {
            val userJson = usersJsonArray.getJSONObject(i)
            val user = Gson().fromJson(userJson.toString(), User::class.java)
            users.add(user)
        }
        return users
    }

    fun getUsersResponsePagingData(users:List<User>): LiveData<PagingData<User>> {
        var usersPaggingData = PagingData.from(users)
        val liveData: MutableLiveData<PagingData<User>> = MutableLiveData<PagingData<User>>()
        liveData.value = usersPaggingData
        return liveData
    }

    @Throws(IOException::class)
    fun readFromFile(filename: String): String {
        val `is`: InputStream = javaClass.getResourceAsStream(filename)
        val stringBuilder = StringBuilder()
        var i: Int
        val b = ByteArray(4096)
        while (`is`.read(b).also { i = it } != -1) {
            stringBuilder.append(String(b, 0, i))
        }
        return stringBuilder.toString()
    }
}