package com.example.taskly.auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val userId: String,
    val fullName: String,
)