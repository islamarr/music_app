package com.islam.music.common


sealed class DataResponse<T> {

    data class Success<T>(
        val data: T?,
    ) : DataResponse<T>()

    data class Failure<T>(
        val reason: String? = null,
    ) : DataResponse<T>()
}