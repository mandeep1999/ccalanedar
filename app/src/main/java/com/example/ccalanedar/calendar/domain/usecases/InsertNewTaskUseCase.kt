package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.core.utils.Utility
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.request.TaskRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertNewTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke(date: Long?, title: String?, description: String?) {
        if (Utility.isValidString(title).not() || Utility.isValidString(description)
                .not() || date == null
        ) {
            return
        }
        withContext(Dispatchers.IO) {
            TaskRequest(date, title, description).let {
                val result = calendarRepository.storeTaskToServer(taskRequest = it)
                if (result is Resource.Success) {
                    val resultResponse = calendarRepository.getAllTasksFromServer()
                    calendarRepository.refreshDB(resultResponse)
                }
            }
        }
    }
}