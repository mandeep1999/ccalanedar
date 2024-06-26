package com.example.ccalanedar.calendar.data.repositories

import androidx.lifecycle.LiveData
import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.data.request.TaskRequest
import com.example.ccalanedar.calendar.data.response.TaskDeletionResponse
import com.example.ccalanedar.calendar.data.response.TaskListResponse
import com.example.ccalanedar.calendar.data.response.TaskStoredResponse
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {

    suspend fun getAllTasksFromServer(): Resource<TaskListResponse>

    suspend fun deleteTaskFromServer(taskId: Int): Resource<TaskDeletionResponse>

    suspend fun storeTaskToServer(taskRequest: TaskRequest): Resource<TaskStoredResponse>

    fun getAllTasksFromClient(): LiveData<List<TaskModelDTO>>

    suspend fun deleteTaskFromClient(taskId: Int)

    suspend fun storeTaskOnClient(taskModelDTO: TaskModelDTO)

    suspend fun refreshDB(result: Resource<TaskListResponse>)
}