package com.example.taskly.feature_auth.domain.repository

import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.LoginRequest
import com.example.taskly.feature_auth.domain.model.LoginResponse
import com.example.taskly.feature_auth.domain.model.RegisterRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse, NetworkError>>

    suspend fun register(registerRequest: RegisterRequest): Flow<Result<Unit, NetworkError>>

    suspend fun logout(): Flow<Result<Unit, NetworkError>>
}