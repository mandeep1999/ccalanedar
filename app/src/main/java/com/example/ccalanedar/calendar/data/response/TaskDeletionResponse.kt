package com.example.ccalanedar.calendar.data.response

import com.google.gson.annotations.SerializedName

data class TaskDeletionResponse(
    @SerializedName("status")
    val status: String? = null
)
