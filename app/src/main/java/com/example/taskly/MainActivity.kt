package com.example.taskly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TasklyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavAuth) {
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

@Composable
fun Calendar() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "You are logged in")
    }
}