package com.example.taskly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.taskly.core.domain.session.UserState
import com.example.taskly.core.presentation.SplashViewModel
import com.example.taskly.feature_auth.presentation.login.LoginScreen
import com.example.taskly.feature_auth.presentation.register.RegisterScreen
import com.example.taskly.ui.navigation.NavAgenda
import com.example.taskly.ui.navigation.NavAuth
import com.example.taskly.ui.navigation.NavCalendar
import com.example.taskly.ui.navigation.NavLoginScreen
import com.example.taskly.ui.navigation.NavRegisterScreen
import com.example.taskly.ui.theme.TasklyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Taskly)
        enableEdgeToEdge()
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                splashViewModel.userState.value == UserState.Authenticating
            }
        }
        setContent {
            val navController = rememberNavController()
            val startingDestination = remember { mutableStateOf<Any?>(null) }

            LaunchedEffect(splashViewModel.userState) {
                splashViewModel.userState.collect { userState ->
                    startingDestination.value = when (userState) {
                        UserState.Authenticated -> NavCalendar
                        UserState.Unauthenticated -> NavAuth
                        UserState.Authenticating -> null
                    }
                }
            }
            startingDestination.value?.let { destination ->
                TasklyTheme {
                    NavHost(navController = navController, startDestination = destination) {
                        navigation<NavAuth>(
                            startDestination = NavLoginScreen,
                        ) {
                            composable<NavLoginScreen> {
                                LoginScreen(navController)
                            }
                            composable<NavRegisterScreen> {
                                RegisterScreen(navController)
                            }
                        }
                        navigation<NavCalendar>(
                            startDestination = NavAgenda
                        ) {
                            composable<NavAgenda> {
                                Calendar()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Calendar() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "You are logged in")
    }
}