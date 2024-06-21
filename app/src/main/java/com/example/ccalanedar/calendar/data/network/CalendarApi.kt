package com.example.ccalanedar.calendar.data.network

import com.example.ccalanedar.calendar.data.request.DeleteTaskRequestModel
import com.example.ccalanedar.calendar.data.request.GetUsersTaskRequestModel
import com.example.ccalanedar.calendar.data.request.StoreTaskRequestModel
import com.example.ccalanedar.calendar.data.response.TaskDeletionResponse
import com.example.ccalanedar.calendar.data.response.TaskListResponse
import com.example.ccalanedar.calendar.data.response.TaskStoredResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarApi {

    @POST("api/getCalendarTaskList")
    suspend fun getCalendarTaskList(@Body getUsersTaskRequestModel: GetUsersTaskRequestModel): Response<TaskListResponse>

    @POST("api/deleteCalendarTask")
    suspend fun deleteCalendarTask(@Body deleteTaskRequestModel: DeleteTaskRequestModel): Response<TaskDeletionResponse>

    @POST("api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body storeTaskRequestModel: StoreTaskRequestModel): Response<TaskStoredResponse>


}