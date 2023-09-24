package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HelperTest {


    lateinit var helper: Helper

    @Before
    fun helper() {
        helper = Helper()
    }

    @Test
    fun isPalendrome_level() {
        //Act
        var result = helper.isPalendrome("level")
        //Assert
        assertEquals(true, result)

    }

    @Test
    fun isPalendrome_empty() {
        //Act
        var result = helper.isPalendrome("")
        //Assert
        assertEquals(true, result)

    }

    @Test
    fun isPalendrome_iphone() {
        //Act
        var result = helper.isPalendrome("iphone")
        //Assert
        assertEquals(true, result)

    }

    @Test
    fun isPalendrome_civic() {
        //Act
        var result = helper.isPalendrome("civic")
        //Assert
        assertEquals(true, result)

    }




    @Test
    fun doLogin_with_five_digit_password(){
        val result = helper.doLogin("civic"!!)
        assertEquals("Password must be 6 characters",result)
//        if(password == null){
//            return "Password must not null"
//        }else if(password.length < 6){
//            return "Password must be 6 characters"
//        }else if(password.length >= 6){
//            return "Password should be less 6 letters"
//        }else if(password.isBlank()){
//            return "Enter password empty not allowed"
//        }
//        return "Sucess"
    }

    @Test
    fun doLogin_with_seven_digit_password() {
        val result = helper.doLogin("civicss")
        assertEquals("Password should be less 6 letters", result)
    }
    @Test
    fun doLogin_with_six_digit_password() {
        val result = helper.doLogin("AdanaA")
        assertEquals("Success", result)
    }

}