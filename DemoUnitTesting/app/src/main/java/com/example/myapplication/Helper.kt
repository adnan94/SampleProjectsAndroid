package com.example.myapplication

import android.util.Log

class Helper {

    fun isPalendrome(value: String):Boolean {

        var result:String = ""
        for (c in value.length-1 downTo 0) {
            result += value[c]
        }

        return result.equals(value,true)

    }

}