package com.example.loginandregistercompoase.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginandregistercompoase.login.data.AuthenticationState
import com.example.loginandregistercompoase.utils.ProgressLoader
import com.example.loginandregistercompoase.utils.SampleLoginDispatchers
import com.example.loginandregistercompoase.utils.combineFlows
import com.example.loginandregistercompoase.utils.stateIn
import com.example.modernandroid13.login.MutableSavedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val dispatchers: SampleLoginDispatchers,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val username = MutableSavedState(
        stateHandle,
        "UserName",
        defaultValue = ""
    )

    private val password = MutableSavedState(
        stateHandle,
        "password",
        defaultValue = ""
    )

    private val passwordVisibility = MutableSavedState(
        stateHandle,
        "password_key",
        defaultValue = false
    )

    private val loadingProgress = ProgressLoader()

    val state = combineFlows(
        username.flow,
        password.flow,
        passwordVisibility.flow,
        loadingProgress.flow
    ) { username, password, passwordToggle, isLoading ->
        AuthenticationState(
            userName = username,
            password = password,
            toggleShowPassword = passwordToggle,
            isLoading = isLoading
        )
    }.stateIn(
        coroutineScope = viewModelScope + dispatchers.main,
        initialValue = AuthenticationState.EMPTY_STATE
    )

    fun userNameChanged(userName: String) {
        username.value = userName
    }

    fun passwordChanged(updatedPassword: String) {
        password.value = updatedPassword
    }

    fun passwordVisibility(visibility: Boolean) {
        passwordVisibility.value = visibility
    }

    fun register() {
        loadingProgress.start()

    }

}