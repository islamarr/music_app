package com.islam.music.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
abstract class SafeServiceCall<T>(
    private val apiCall: (suspend () -> T?)? = null,
    private val cacheCall: (suspend () -> T?)? = null
) {

    suspend fun safeCall(): DataResponse<T> {
        cacheCall?.let {
            safeCacheCall(it)
        }

        apiCall?.let {
            return safeApiCall(it)
        } ?: return safeCacheCall(cacheCall)
    }

    private suspend fun safeApiCall(
        apiCall: (suspend () -> T?)?
    ): DataResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall?.invoke()
                result?.let {
                    DataResponse.Success(it)
                } ?: DataResponse.Failure()

            } catch (throwable: Throwable) {
                cacheCall?.let {
                    safeCacheCall(it)
                } ?: when (throwable) {
                    is HttpException -> {
                        DataResponse.Failure(throwable.response()?.errorBody().toString())
                    }
                    else -> {
                        DataResponse.Failure(throwable.message)
                    }
                }
            }
        }
    }

    private suspend fun safeCacheCall(
        cacheCall: (suspend () -> T?)?
    ): DataResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val result = cacheCall?.invoke()
                result?.let {
                    DataResponse.Success(it)
                } ?: DataResponse.Failure()
            } catch (e: Exception) {
                DataResponse.Failure(e.message)
            }
        }
    }

}