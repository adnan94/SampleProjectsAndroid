package com.example.scalioapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class ApplicationUtils {
    companion object{


        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var netInfo: NetworkInfo? = null
            netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

    }

}