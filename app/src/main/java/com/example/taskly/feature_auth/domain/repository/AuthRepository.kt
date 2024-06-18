package com.example.taskly.feature_auth.domain.repository

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.LoginRequest
import com.example.taskly.feature_auth.domain.model.LoginResponse
import com.example.taskly.feature_auth.domain.model.RegisterRequest

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, NetworkError>

    suspend fun register(registerRequest: RegisterRequest): Result<Unit, NetworkError>

    suspend fun logout(): Result<Unit, NetworkError>

    suspend fun authenticate(bearerToken: String): Result<Unit, NetworkError>

    suspend fun storeJwtToken(token: String)

    suspend fun getJwtToken(): String?
}