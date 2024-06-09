package com.example.taskly.feature_auth.domain.model

data class LoginRequest(
    val email: String,
    val password: String,
)
