package com.example.ccalanedar.calendar.data.request

import com.google.gson.annotations.SerializedName

data class DeleteTaskRequestModel(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("task_id")
    val taskId: Int
)
