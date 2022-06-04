package com.islam.music.common

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

abstract class NetworkBoundResource<NetworkObj, CacheObj, ViewState>(
    private val dispatcher: CoroutineDispatcher,
    private val apiCall: (suspend () -> NetworkObj?)? = null,
    val cacheCall: (suspend () -> CacheObj?)? = null
) {

    val result: Flow<DataState<ViewState>> = flow {
        Log.d("zzzz", "result: Flow<DataState<: ")
        emit(DataState.LOADING(isLoading = true))
        cacheCall?.let {
            emitAll(safeCacheCall())
        }

        apiCall?.let {
            Log.d("zzzz", "apiCall?.let { ")
            emitAll(safeAPICall())
        }
        emit(DataState.LOADING(isLoading = false))
    }

    private suspend fun safeAPICall() = flow<DataState<ViewState>> {
        withContext(dispatcher) {
            try {
                Log.d("zzzz", "  try { ")
                // throws TimeoutCancellationException
                withTimeout(NETWORK_TIMEOUT) {
                    val result = apiCall?.invoke()
                    Log.d("zzzz", "NETWORK_TIMEOUT ")
                    if (result == null) {
                        Log.d("zzzz", "result == null ")
                        emit(DataState.ERROR(UNKNOWN_ERROR))
                    } else {
                        Log.d("zzzz", "result != null ")
                        updateCache(result)
                        handleNetworkSuccess(result)?.let {
                            emit(it)
                        }
                    }
                }
            } catch (throwable: Throwable) {
                Log.d("zzzz", "throwable ${throwable}")
                throwable.printStackTrace()
                when (throwable) {
                    is TimeoutCancellationException -> {
                        emit(DataState.ERROR(NETWORK_ERROR_TIMEOUT))
                    }
                    is IOException -> {
                        emit(DataState.ERROR(NETWORK_ERROR))
                    }
                    is HttpException -> {
                        emit(DataState.ERROR(convertErrorBody(throwable)))
                    }
                    else -> {
                        emit(DataState.ERROR(UNKNOWN_ERROR))
                    }
                }
            }
        }
    }

    private suspend fun safeCacheCall() = flow<DataState<ViewState>> {
        withContext(dispatcher) {
            try {
                // throws TimeoutCancellationException
                withTimeout(CACHE_TIMEOUT) {
                    val result = cacheCall?.invoke()
                    handleCacheSuccess(result)?.let {
                        emit(it)
                    }
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is TimeoutCancellationException -> {
                        emit(DataState.ERROR(CACHE_ERROR_TIMEOUT))
                    }
                    else -> {
                        emit(DataState.ERROR(UNKNOWN_ERROR))
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): String {
        return try {
            throwable.response()?.errorBody()?.string() ?: UNKNOWN_ERROR
        } catch (exception: Exception) {
            UNKNOWN_ERROR
        }
    }

    open suspend fun handleNetworkSuccess(response: NetworkObj):
            DataState<ViewState>? {
        Log.d("zzzz", "handleNetworkSuccess: ")
        return null
    }

    open suspend fun handleCacheSuccess(response: CacheObj?):
            DataState<ViewState>? {
        Log.d("zzzz", "handleCacheSuccess: ")
        return null
    }

    open suspend fun updateCache(networkObject: NetworkObj) {
        Log.d("zzzz", "updateCache: ")
    }
}
