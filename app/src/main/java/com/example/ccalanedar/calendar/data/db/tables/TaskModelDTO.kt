package com.example.ccalanedar.calendar.data.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("tasks_list")
data class TaskModelDTO(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("task_id")
    @SerializedName("task_id")
    val taskId: Int,
    @SerializedName("title")
    @ColumnInfo("title")
    val title: String? = null,
    @SerializedName("description")
    @ColumnInfo("description")
    val description: String? = null,
    @SerializedName("date")
    @ColumnInfo("date")
    val date: Long? = null
)
