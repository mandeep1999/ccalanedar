package com.example.ccalanedar.calendar.data.request

import com.google.gson.annotations.SerializedName

data class StoreTaskRequestModel(
    @SerializedName("user_id")
    val userID: Int,
    @SerializedName("task")
    val task: TaskRequest,
)

data class TaskRequest(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null
)
