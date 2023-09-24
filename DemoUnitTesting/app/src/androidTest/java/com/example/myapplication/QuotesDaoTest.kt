package com.example.myapplication

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuotesDaoTest {
    var quotesDatabase: QuoteDatabase? = null
    lateinit var quotesDao: QuotesDao

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        quotesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            QuoteDatabase::class.java
        ).allowMainThreadQueries().build()
        quotesDao = quotesDatabase!!.quoteDao()
    }

    @Test
    fun insertQuote() = runBlocking {
        val quote = Quote(0, "This is a test quote", "Adnan Ahmed")
        quotesDao.insertQuote(quote)
        val result = quotesDao.getQuotes().getOrAwaitValue() //wait for max 2 seconds and send value make livedata for syncronous execute
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("This is a test quote", result[0].text)
    }

    @After
    fun tearDown() {
        quotesDatabase?.close()
    }
}