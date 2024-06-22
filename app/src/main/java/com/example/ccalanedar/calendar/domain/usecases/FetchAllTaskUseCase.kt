package com.example.ccalanedar.calendar.domain.usecases

import com.example.ccalanedar.calendar.core.utils.Resource
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Mandeep Singh on 21 June 2024
 * @param calendarRepository to get all the tasks from server,
 * and then refresh the DB.
 */
class FetchAllTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    /**
     * @param none
     * It gets all the tasks from the server, and on its success,
     * refresh the local DB.
     */
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val result = calendarRepository.getAllTasksFromServer()
            if (result is Resource.Success){
                calendarRepository.refreshDB(result)
            }
        }
    }
}