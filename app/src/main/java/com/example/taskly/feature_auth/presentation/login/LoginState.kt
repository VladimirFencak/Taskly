package com.example.taskly.feature_auth.presentation.login

import com.example.taskly.core.domain.errors.NetworkError

data class LoginState(
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val isLoggedIn: Boolean = false,
    val loginName: String = "",
    val isValidEmail: Boolean = false,
    val loginPwd: String = ""
)