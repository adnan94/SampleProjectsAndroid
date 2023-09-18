package com.example.myapplication

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParametrizedPalendrome(val input: String, val expectedResult: Boolean) {

    lateinit var helper: Helper

    @Before
    fun helper() {
        helper = Helper()
    }

    @Test
    fun test() {
        var result = helper.isPalendrome(input)
        Assert.assertEquals(expectedResult, result)
    }

    
}