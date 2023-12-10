package com.example.loginandregistercompoase.login

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
class LoginViewModel @Inject constructor(
    dispatchers: SampleLoginDispatchers,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val password = MutableSavedState(
        savedStateHandle,
        "password",
        defaultValue = ""
    )

    private val userName = MutableSavedState(
        savedStateHandle,
        "UserName",
        defaultValue = ""
    )
    private val passwordVisibility = MutableSavedState(
        savedStateHandle,
        "password_key",
        defaultValue = false
    )
    private val loadingProgress = ProgressLoader()

    val state = combineFlows(
        userName.flow,
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




    fun userNameChange(newValue:String){
        userName.value = newValue
    }

    fun passwordChanged(updatedPassword: String) {
        password.value = updatedPassword
    }

    fun passwordVisibility(visibility: Boolean) {
        passwordVisibility.value = visibility
    }

    fun login() {
        loadingProgress.start()

    }

}