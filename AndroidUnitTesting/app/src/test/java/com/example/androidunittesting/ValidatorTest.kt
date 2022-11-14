package com.example.androidunittesting

import com.google.common.truth.Truth.assertThat
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {

    @BeforeClass
    fun init(){
        System.out.println("This will run always when test start")
    }


    @Before
    fun initMethod(){
        System.out.println("This will run always before each test method start")
    }

    @Test
    fun whenInputIsValid(){
        val amount = 100
        val desc = "Some random desc"
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsInvalid(){
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount, desc)
        assertThat(result).isEqualTo(false)
    }

    @After
    fun afterMethod(){
        System.out.println("This will run always after each test method end")
    }

    @AfterClass
    fun close(){
        System.out.println("This will run always when test end")
    }
}