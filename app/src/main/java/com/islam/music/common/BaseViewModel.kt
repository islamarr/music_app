package com.islam.music.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATES : ViewState, ACTIONS : Action, RESULTS : Result>(val initialState: STATES) :
    ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATES>
        get() = _state.asStateFlow()

    fun dispatch(actions: ACTIONS) {
        handle(actions).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: STATES) {
        _state.emit(state)
    }

    abstract fun reduce(result: RESULTS): STATES

    abstract fun handle(actions: ACTIONS): Flow<RESULTS>

}