package com.madonasyombua.samplelogin.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.loginandregistercompoase.register.RegisterContent
import com.example.loginandregistercompoase.register.RegisterViewModel

@Composable
fun RegisterContentScreen(
    registerViewModel: RegisterViewModel,
) {
    val viewState by registerViewModel.state.collectAsState()

    RegisterContent(
        uiState = viewState,
        onUsernameUpdated = registerViewModel::userNameChanged,
        onPasswordUpdated = registerViewModel::passwordChanged,
        onLogin = registerViewModel::register,
        passwordToggleVisibility = registerViewModel::passwordVisibility,
    )
}