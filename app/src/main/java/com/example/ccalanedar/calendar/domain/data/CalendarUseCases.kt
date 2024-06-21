package com.example.ccalanedar.calendar.domain.data

import com.example.ccalanedar.calendar.domain.usecases.DeleteTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.FetchAllTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.InsertNewTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.ReadAllTaskUseCase

data class CalendarUseCases(
    val readAllTaskUseCase: ReadAllTaskUseCase,
    val insertNewTaskUseCase: InsertNewTaskUseCase,
    val fetchAllTaskUseCase: FetchAllTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
)
