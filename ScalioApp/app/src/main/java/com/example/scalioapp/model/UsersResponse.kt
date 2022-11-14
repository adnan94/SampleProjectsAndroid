package com.example.scalioapp.model

import com.google.gson.annotations.SerializedName

class UsersResponse(
    @SerializedName("items") val results: List<User>?,
    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("incomplete_results") var incompleteResults: Boolean
)