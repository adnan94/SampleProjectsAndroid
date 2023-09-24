package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuotesDao { // we need to test these quries because we create quries by our self

    @Insert
    suspend fun insertQuote(quote: Quote)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM quote")
    suspend fun deleteQuote()

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM quote WHERE id= :id")
    suspend fun getQuoteByID(id: Int): Quote

}