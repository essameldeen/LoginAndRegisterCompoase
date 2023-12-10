package com.example.loginandregistercompoase.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginandregistercompoase.R
import com.example.loginandregistercompoase.login.components.LoginButton
import com.example.loginandregistercompoase.login.components.PasswordInputField
import com.example.loginandregistercompoase.login.components.UserNameField
import com.example.loginandregistercompoase.login.data.AuthenticationState
import com.madonasyombua.samplelogin.util.TestTags
import com.madonasyombua.samplelogin.util.TestTags.LoginContent.LOGO_IMAGE


@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: AuthenticationState,
    onUserNameChange :(String) ->Unit,
    onPasswordChange :(String) ->Unit,
    onLogin :()->Unit,
    passwordToggleVisibility : (Boolean)-> Unit,
    onRegister: () -> Unit
){

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_700)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = modifier
                    .testTag(LOGO_IMAGE),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.logo)
            )

        }

        Card(
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_700))
                .weight(5.0f)

        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .scrollable(scrollState, Orientation.Vertical),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                UserNameField(authState = uiState, onValueChanged = onUserNameChange)
                PasswordInputField(
                    text = stringResource(id = R.string.password),
                    authState = uiState,
                    onValueChanged = onPasswordChange,
                    passwordToggleVisibility = passwordToggleVisibility
                )
                LoginButton(
                    text = stringResource(id = R.string.sign_in),
                    enabled = if (uiState.isValidForm()) {
                        !uiState.isLoading
                    } else {
                        false
                    },
                    onLoginClicked = {
                        onLogin.invoke()
                    },
                    isLoading = uiState.isLoading
                )

                ClickableText(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .testTag(TestTags.LoginContent.REGISTER_USER),
                    text = AnnotatedString(stringResource(id = R.string.register)),
                    onClick = { onRegister.invoke() },
                    style = TextStyle(
                        colorResource(id = R.color.purple_700),
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Composable
fun CheckableSwitch() {
    Switch(checked = false, onCheckedChange = {})
}

@Composable
fun NonCheckableSwitch() {
    Switch(checked = false, onCheckedChange = null)
}
