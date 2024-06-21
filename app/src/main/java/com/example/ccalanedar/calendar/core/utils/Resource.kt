package com.example.ccalanedar.calendar.core.utils

sealed class Resource<T>(
    private val data: T? = null,
    private val message: String? = null
) {

    class Success<T>(data: T?) : Resource<T>(data = data)

    class Error<T>(errorMessage: String?) : Resource<T>(message = errorMessage)

    class Loading<T> : Resource<T>()

}