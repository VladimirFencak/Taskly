package com.example.taskly.feature_auth.data.remote.dto

import com.example.taskly.feature_auth.domain.model.LoginRequest
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String,
)

fun LoginRequestDto.toLoginRequest() = LoginRequest(
    email = this.email,
    password = this.password
)

fun LoginRequest.toLoginRequestDto() = LoginRequestDto(
    email = this.email,
    password = this.password,
)