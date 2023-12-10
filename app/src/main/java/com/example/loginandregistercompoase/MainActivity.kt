package com.example.loginandregistercompoase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginandregistercompoase.login.LoginContentScreen
import com.example.loginandregistercompoase.ui.theme.LoginAndRegisterCompoaseTheme
import com.example.loginandregistercompoase.utils.Destination
import com.madonasyombua.samplelogin.login.RegisterContentScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginAndRegisterCompoaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Destination.LoginScreen.route) {
        composable(Destination.LoginScreen.route) {
            LoginContentScreen(loginViewModel = hiltViewModel(), onRegisterNavigateTo = {
                navController.navigate(Destination.RegisterScreen.route)
            })
        }
        composable(Destination.RegisterScreen.route) {
            RegisterContentScreen(registerViewModel = hiltViewModel())

        }

    }
}




