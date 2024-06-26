package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.request.TaskRequest
import com.example.ccalanedar.calendar.data.utility.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mandeep Singh on 21 June 2024
 * @param calendarRepository to insert the new task into the server, and
 * then on success, get all tasks back from server and reinsert them into the room.
 */
class InsertNewTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke(date: Long?, title: String?, description: String?) {
        withContext(Dispatchers.IO) {
            if (Utility.isValidTask(title, description, date).not()) {
                return@withContext
            }
            TaskRequest(date!!, title, description).let {
                val result = calendarRepository.storeTaskToServer(taskRequest = it)
                if (result is Resource.Success) {
                    val resultResponse = calendarRepository.getAllTasksFromServer()
                    calendarRepository.refreshDB(resultResponse)
                }
            }
        }
    }
}