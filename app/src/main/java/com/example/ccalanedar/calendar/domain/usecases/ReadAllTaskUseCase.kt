package com.example.ccalanedar.calendar.domain.usecases

import androidx.lifecycle.LiveData
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mandeep Singh on 21 June 2024
 * @param calendarRepository to fetch all the tasks from the room db.
 */
class ReadAllTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    operator fun invoke(): LiveData<List<TaskModelDTO>> {
        return calendarRepository.getAllTasksFromClient()
    }
}