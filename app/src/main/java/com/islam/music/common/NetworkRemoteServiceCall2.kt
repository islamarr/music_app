package com.islam.music.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
abstract class NetworkRemoteServiceCall2<T>(
    private val apiCall: (suspend () -> T?)? = null,
    private val cacheCall: (suspend () -> T?)? = null
) {

    suspend fun safeCall(): NetworkResponse<T> {
        cacheCall?.let {
            safeCacheCall(it)
        }

        apiCall?.let {
            return safeApiCall(it)
        } ?: return safeCacheCall(cacheCall)
    }

    suspend fun safeApiCall(
        apiCall: (suspend () -> T?)?
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiCall?.invoke()
                result?.let {
                    NetworkResponse.Success(it)
                } ?: NetworkResponse.Failure(0, "")

            } catch (throwable: Throwable) {
                cacheCall?.let {
                    safeCacheCall(it)
                } ?: when (throwable) {
                    is HttpException -> {
                        NetworkResponse.Failure(
                            throwable.code(),
                            throwable.response()?.errorBody().toString()
                        )
                    }
                    else -> {
                        NetworkResponse.Failure(0, throwable.message)
                    }
                }
            }
        }
    }

    suspend fun safeCacheCall(
        cacheCall: (suspend () -> T?)?
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                val result = cacheCall?.invoke()
                result?.let {
                    NetworkResponse.Success(it)
                } ?: NetworkResponse.Failure(0, "")
            } catch (e: Exception) {
                NetworkResponse.Failure(0, e.message)
            }
        }
    }

}