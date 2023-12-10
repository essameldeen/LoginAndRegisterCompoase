package com.example.loginandregistercompoase.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun  LoginContentScreen(
    loginViewModel: LoginViewModel,
    onRegisterNavigateTo :()-> Unit
){
    val viewState by loginViewModel.state.collectAsState()
    LoginContent(
        uiState = viewState,
        onUserNameChange = loginViewModel::userNameChange,
        onPasswordChange = loginViewModel::passwordChanged,
        onLogin = loginViewModel::login,
        passwordToggleVisibility = loginViewModel::passwordVisibility,
        onRegister = onRegisterNavigateTo
    )
}