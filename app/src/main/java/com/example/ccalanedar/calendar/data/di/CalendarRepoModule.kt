package com.example.ccalanedar.calendar.data.di

import com.example.ccalanedar.calendar.data.repositories.CalendarRepository
import com.example.ccalanedar.calendar.data.repositories.CalendarRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CalendarRepoModule {

    @Binds
    abstract fun bindCalendarRepository(calendarRepositoryImpl: CalendarRepositoryImpl): CalendarRepository
}