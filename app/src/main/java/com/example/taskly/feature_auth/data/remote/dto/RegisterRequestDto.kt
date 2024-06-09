package com.example.taskly.feature_auth.data.remote.dto

import com.example.taskly.feature_auth.domain.model.RegisterRequest
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val fullName: String,
    val email: String,
    val password: String,
)

fun RegisterRequestDto.toRegisterRequest() = RegisterRequest(
    fullName = this.fullName,
    email = this.email,
    password = this.password
)

fun RegisterRequest.toRegisterRequestDto() = RegisterRequestDto(
    fullName = this.fullName,
    email = this.email,
    password = this.password
)

