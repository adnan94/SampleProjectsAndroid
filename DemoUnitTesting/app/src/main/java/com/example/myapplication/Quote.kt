package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote( // no need to test
    @PrimaryKey
    val id:Int,
    val text:String,
    val author:String
)