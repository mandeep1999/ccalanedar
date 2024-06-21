package com.example.ccalanedar.calendar.data.repositories

import androidx.lifecycle.LiveData
import com.example.ccalanedar.calendar.core.database.AppDataBase
import com.example.ccalanedar.calendar.core.utils.BaseRepo
import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.data.network.CalendarApi
import com.example.ccalanedar.calendar.data.request.DeleteTaskRequestModel
import com.example.ccalanedar.calendar.data.request.GetUsersTaskRequestModel
import com.example.ccalanedar.calendar.data.request.StoreTaskRequestModel
import com.example.ccalanedar.calendar.data.request.TaskRequest
import com.example.ccalanedar.calendar.data.response.TaskDeletionResponse
import com.example.ccalanedar.calendar.data.response.TaskListResponse
import com.example.ccalanedar.calendar.data.response.TaskStoredResponse
import com.example.ccalanedar.calendar.data.utility.DTOConverter.convertTaskItemToTaskDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalendarRepositoryImpl @Inject constructor(
    private val calendarApi: CalendarApi,
    private val appDataBase: AppDataBase
) : BaseRepo(), CalendarRepository {

    private val userID: Int = 1239

    override suspend fun getAllTasksFromServer(): Resource<TaskListResponse> {
        val requestModel = GetUsersTaskRequestModel(userId = userID)
        return safeApiCall { calendarApi.getCalendarTaskList(requestModel) }
    }


    override suspend fun deleteTaskFromServer(taskId: Int): Resource<TaskDeletionResponse> {
        val requestModel = DeleteTaskRequestModel(userId = userID, taskId = taskId)
        return safeApiCall { calendarApi.deleteCalendarTask(requestModel) }
    }

    override suspend fun storeTaskToServer(taskRequest: TaskRequest): Resource<TaskStoredResponse> {
        val requestModel = StoreTaskRequestModel(userID = userID, task = taskRequest)
        return safeApiCall { calendarApi.storeCalendarTask(requestModel) }
    }

    override fun getAllTasksFromClient(): LiveData<List<TaskModelDTO>> {
        return appDataBase.calendarDAO.getAllTasksFromDB()
    }

    override suspend fun deleteTaskFromClient(taskId: Int) {
        return appDataBase.calendarDAO.deleteTaskFromDB(taskId)
    }

    override suspend fun storeTaskOnClient(taskModelDTO: TaskModelDTO) {
        return appDataBase.calendarDAO.insertTaskIntoDB(taskModelDTO = taskModelDTO)
    }

    override suspend fun refreshDB(result: Resource<TaskListResponse>) {
        if (result is Resource.Success) {
            appDataBase.calendarDAO.deleteAllTasks()
            result.data?.tasks?.map {
                convertTaskItemToTaskDTO(it)?.let { dto ->
                    appDataBase.calendarDAO.insertTaskIntoDB(dto)
                }
            }
        }
    }
}