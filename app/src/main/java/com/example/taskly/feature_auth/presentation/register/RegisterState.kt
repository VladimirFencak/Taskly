package com.example.taskly.feature_auth.presentation.register

import com.example.taskly.core.domain.errors.NetworkError

data class RegisterState(
    val isLoading: Boolean = false,
    val error: NetworkError? = null,
    val registered: Boolean = false,
    val email: String = "",
    val name: String = "",
    val pwd: String = "",
    val isValidEmail: Boolean = false,
    val isValidName: Boolean = false
)