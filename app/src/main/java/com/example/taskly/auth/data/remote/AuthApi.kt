package com.example.taskly.auth.data.remote

import com.example.taskly.auth.data.remote.dto.LoginRequest
import com.example.taskly.auth.data.remote.dto.LoginResponse
import com.example.taskly.auth.data.remote.dto.RegisterRequest
import com.example.taskly.auth.domain.errors.NetworkError
import com.example.taskly.auth.domain.errors.Result

interface AuthApi {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, NetworkError>

    suspend fun register(registerRequest: RegisterRequest): Result<Unit, NetworkError>

    suspend fun logOut(): Result<Unit, NetworkError>
}