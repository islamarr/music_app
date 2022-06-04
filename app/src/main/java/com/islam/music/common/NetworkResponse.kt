package com.islam.music.common


sealed class NetworkResponse<T> { //TODO add loading class here
    data class Success<T>(
        val data: T?,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val httpCode: Int,
        val reason: String? = null,
    ) : NetworkResponse<T>()
}