package com.example.ccalanedar.calendar.data.response

import com.google.gson.annotations.SerializedName

data class TaskListResponse(
    @SerializedName("tasks")
    val tasks: List<TaskItem>? = null
)
