package com.example.scalioapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    var id: Int,
    var login: String,
    var node_id: String,
    var avatar_url: String,
    var gravatar_id: String,
    var url: String,
    var type: String,
    var received_events_url: String,
    var events_url: String,
    var repos_url: String,
    var organizations_url: String,
    var html_url: String,
    var followers_url: String,
    var following_url: String,
    var gists_url: String,
    var starred_url: String,
    var subscriptions_url: String,
    var site_admin: Boolean,
    var score: Double
)