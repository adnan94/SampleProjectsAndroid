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

    fun doLogin(password:String?):String{
        if(password == null){
            return "Password must not null"
        }else if(password.length==6){
            return "Success"
        }else if(password.length < 6){
          return "Password must be 6 characters"
        }else if(password.length > 6){
            return "Password should be less 6 letters"
        }else if(password.isBlank()){
            return "Enter password empty not allowed"
        }
        return ""
    }

}