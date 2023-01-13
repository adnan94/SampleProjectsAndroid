package com.example.encryptedprefrences
import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val username: String? = null
)