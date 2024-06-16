package com.example.taskly.feature_auth.domain.model

data class LoginResponse(
    val accessToken: String,
    val userId: String,
    val fullName: String,
)
