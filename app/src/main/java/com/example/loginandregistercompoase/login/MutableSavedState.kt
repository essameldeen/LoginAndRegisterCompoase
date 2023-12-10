package com.example.modernandroid13.login

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow

class MutableSavedState<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String,
    defaultValue: T

) {

    private val _state: MutableStateFlow<T> = MutableStateFlow(
        savedStateHandle.get<T>(key) ?: defaultValue
    )

    var value: T
        get() = _state.value
        set(value) {
            _state.value = value
            savedStateHandle[key] = value
        }

    val flow get() = _state

}
