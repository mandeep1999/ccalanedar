package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.response.TaskListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAllTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val result = calendarRepository.getAllTasksFromServer()
            if (result is Resource.Success){
                calendarRepository.refreshDB(result)
            }
        }
    }
}