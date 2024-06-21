package com.example.ccalanedar.calendar.domain.usecases

import androidx.lifecycle.LiveData
import com.example.ccalanedar.calendar.data.db.tables.TaskModelDTO
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadAllTaskUseCase @Inject constructor(private val calendarRepository: CalendarRepository) {

    operator fun invoke(): LiveData<List<TaskModelDTO>> {
        return calendarRepository.getAllTasksFromClient()
    }
}