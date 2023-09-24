package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() { //no need for unit test cus its google frame work
    abstract fun quoteDao():QuotesDao
}