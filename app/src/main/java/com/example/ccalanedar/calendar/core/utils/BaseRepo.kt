package com.example.ccalanedar.calendar.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepo() {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    return@withContext Resource.Success(data = response.body()!!)
                } else {
                    return@withContext Resource.Error(
                        errorMessage = response.errorBody().toString()
                    )
                }
            } catch (e: HttpException) {
                return@withContext Resource.Error(errorMessage = "Something went wrong!!")
            } catch (e: IOException) {
                return@withContext Resource.Error(errorMessage = "Something went wrong!!")
            } catch (e: Exception) {
                return@withContext Resource.Error(errorMessage = "Something went wrong!!")
            }
        }
    }
}