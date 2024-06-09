package com.example.taskly.feature_auth.domain.model

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String,
)
