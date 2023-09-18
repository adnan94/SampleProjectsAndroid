package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PalendromeTest {


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
}