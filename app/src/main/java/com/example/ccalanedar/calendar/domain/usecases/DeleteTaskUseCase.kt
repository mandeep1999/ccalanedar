package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.utility.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mandeep Singh on 21 June 2024
 * @param calendarRepository to delete the task from server,
 * and on success delete it from room db.
 */
class DeleteTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    /**
     * @param taskId -> the task to be deleted. It first deletes from
     * server, and on it success clear it from room db.
     */
    suspend operator fun invoke(taskId: Int?) {
        withContext(Dispatchers.IO) {
            if (Utility.isValidTaskId(taskId).not()) {
                return@withContext
            }
            val result = calendarRepository.deleteTaskFromServer(taskId!!)
            if (result is Resource.Success) {
                calendarRepository.deleteTaskFromClient(taskId)
            }
        }
    }
}