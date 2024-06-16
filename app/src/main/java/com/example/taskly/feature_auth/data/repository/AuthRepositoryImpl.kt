package com.example.taskly.feature_auth.data.repository

import com.example.taskly.feature_auth.data.remote.AuthApi
import com.example.taskly.feature_auth.data.remote.dto.toLoginRequestDto
import com.example.taskly.feature_auth.data.remote.dto.toLoginResponse
import com.example.taskly.feature_auth.data.remote.dto.toRegisterRequestDto
import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.LoginRequest
import com.example.taskly.feature_auth.domain.model.LoginResponse
import com.example.taskly.feature_auth.domain.model.RegisterRequest
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, NetworkError> {
        authApi.login(loginRequest.toLoginRequestDto()).also {
            return when (it) {
                is Result.Success -> Result.Success(it.data.toLoginResponse())
                is Result.Error -> Result.Error(it.error)
            }
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<Unit, NetworkError> {
        return authApi.register(registerRequest.toRegisterRequestDto())
    }
}