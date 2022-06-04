package com.islam.music.common

sealed class DataState<T>(
    var loading: Boolean,
    var data: T? = null,
    var stateMessage: String? = null
) {
    class INITIAL<T>(isLoading: Boolean) : DataState<T>(isLoading)

    class LOADING<T>(
        isLoading: Boolean,
        cachedData: T? = null
    ) : DataState<T>(
        loading = isLoading,
        data = cachedData
    )

    class SUCCESS<T>(
        data: T? = null,
        stateMessage: String? = null
    ) : DataState<T>(
        loading = false,
        data = data,
        stateMessage = stateMessage
    )

    class ERROR<T>(
        stateMessage: String
    ) : DataState<T>(
        loading = false,
        data = null,
        stateMessage = stateMessage
    )
}
