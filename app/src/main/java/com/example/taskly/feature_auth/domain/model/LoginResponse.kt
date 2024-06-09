package com.example.taskly.feature_auth.domain.model

data class LoginResponse(
    val token: String,
    val userId: String,
    val fullName: String,
)
