package com.example.ccalanedar.calendar.data.response

import com.google.gson.annotations.SerializedName

data class TaskItem(
    @SerializedName("task_id")
    val taskID: Int? = null,
    @SerializedName("task_detail")
    val taskDetail: TaskDetail? = null
)

data class TaskDetail(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("date")
    val date: Long? = null
)

