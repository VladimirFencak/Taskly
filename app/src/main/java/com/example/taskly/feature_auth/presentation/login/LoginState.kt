package com.example.taskly.feature_auth.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val loginName: String = "",
    val loginPwd: String = ""
)
