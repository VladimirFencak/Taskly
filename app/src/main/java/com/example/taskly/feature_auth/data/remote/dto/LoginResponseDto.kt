package com.example.taskly.feature_auth.data.remote.dto

import com.example.taskly.feature_auth.domain.model.LoginResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String,
    val userId: String,
    val fullName: String,
)

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    token = this.token,
    userId = this.userId,
    fullName = this.fullName
)

fun LoginResponse.toLoginResponseDto() = LoginResponseDto(
    token = this.token,
    userId = this.userId,
    fullName = this.fullName
)