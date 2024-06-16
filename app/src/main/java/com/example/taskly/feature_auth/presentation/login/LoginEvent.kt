package com.example.taskly.feature_auth.presentation.login

sealed class LoginEvent {
    data object Login : LoginEvent()
    data class OnLoginNameChange(val name: String) : LoginEvent()
    data class OnLoginPwdChange(val pwd: String) : LoginEvent()
}