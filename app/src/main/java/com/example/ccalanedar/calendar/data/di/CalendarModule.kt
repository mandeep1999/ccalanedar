package com.example.ccalanedar.calendar.data.di

import com.example.ccalanedar.calendar.data.network.CalendarApi
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
}