package com.example.myapplication

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(value = Parameterized::class)
class ParametrizedPalendrome(val input: String, val expectedResult: Boolean) {

    lateinit var helper: Helper

    @Before
    fun helper() {
        helper = Helper()
    }

    @Test
    fun test() {
        val result = helper.isPalendrome(input)
        Assert.assertEquals(expectedResult, result)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "")
        fun data():List<Array<Any>>{
            return listOf(
                arrayOf("hello",false),
                arrayOf("level",true),
                arrayOf("a",true),
                arrayOf("",true))
        }
    }

}