package com.example.taskly.feature_auth.presentation.login

import com.example.taskly.feature_auth.domain.errors.NetworkError

data class LoginState(
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val loginName: String = "",
    val isValidEmail: Boolean = false,
    val loginPwd: String = ""
)