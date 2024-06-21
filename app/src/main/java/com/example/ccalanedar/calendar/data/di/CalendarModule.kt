package com.example.ccalanedar.calendar.data.di

import com.example.ccalanedar.calendar.data.network.CalendarApi
import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.repositories.CalendarRepositoryImpl
import com.example.ccalanedar.calendar.domain.data.CalendarUseCases
import com.example.ccalanedar.calendar.domain.usecases.DeleteTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.FetchAllTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.InsertNewTaskUseCase
import com.example.ccalanedar.calendar.domain.usecases.ReadAllTaskUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CalendarModule {

    @Singleton
    @Provides
    fun providesCalendarApi(retrofit: Retrofit): CalendarApi {
        return retrofit.create(CalendarApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDeleteTaskUseCase(calendarRepository: CalendarRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(calendarRepository)
    }

    @Singleton
    @Provides
    fun providesFetchAllTaskUseCase(calendarRepository: CalendarRepository): FetchAllTaskUseCase {
        return FetchAllTaskUseCase(calendarRepository)
    }

    @Singleton
    @Provides
    fun providesInsertTaskUseCase(calendarRepository: CalendarRepository): InsertNewTaskUseCase {
        return InsertNewTaskUseCase(calendarRepository)
    }

    @Singleton
    @Provides
    fun providesReadTaskUseCase(calendarRepository: CalendarRepository): ReadAllTaskUseCase {
        return ReadAllTaskUseCase(calendarRepository)
    }

    @Singleton
    @Provides
    fun providesCalendarUseCases(
        deleteTaskUseCase: DeleteTaskUseCase,
        fetchAllTaskUseCase: FetchAllTaskUseCase,
        insertNewTaskUseCase: InsertNewTaskUseCase,
        readAllTaskUseCase: ReadAllTaskUseCase
    ): CalendarUseCases {
        return CalendarUseCases(
            deleteTaskUseCase = deleteTaskUseCase,
            fetchAllTaskUseCase = fetchAllTaskUseCase,
            insertNewTaskUseCase = insertNewTaskUseCase,
            readAllTaskUseCase = readAllTaskUseCase
        )
    }

}