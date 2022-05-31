package com.islam.music.common


sealed class NetworkResponse<T> {
    data class Success<T>(
        val data: T?,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val httpCode: Int,
        val reason: String? = null,
    ) : NetworkResponse<T>()
}