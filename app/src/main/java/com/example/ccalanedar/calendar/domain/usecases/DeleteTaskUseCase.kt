package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.utility.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    suspend operator fun invoke(taskId: Int?) {
        withContext(Dispatchers.IO) {
            if (Utility.isValidTaskId(taskId)) {
                return@withContext
            }
            val result = calendarRepository.deleteTaskFromServer(taskId!!)
            if (result is Resource.Success) {
                calendarRepository.deleteTaskFromClient(taskId)
            }
        }
    }
}