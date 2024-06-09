package com.example.taskly.feature_auth.data.remote

import com.example.taskly.feature_auth.data.remote.dto.LoginRequestDto
import com.example.taskly.feature_auth.data.remote.dto.LoginResponseDto
import com.example.taskly.feature_auth.data.remote.dto.RegisterRequestDto
import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.domain.errors.Result

interface AuthApi {
    suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginResponseDto, NetworkError>

    suspend fun register(registerRequestDto: RegisterRequestDto): Result<Unit, NetworkError>

    suspend fun logOut(): Result<Unit, NetworkError>
}