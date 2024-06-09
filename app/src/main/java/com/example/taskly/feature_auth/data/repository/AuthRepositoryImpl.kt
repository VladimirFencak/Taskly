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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<Result<LoginResponse, NetworkError>> {
        return flow {
            authApi.login(loginRequest.toLoginRequestDto()).also { resultDto ->
                emit(
                    when (resultDto) {
                        is Result.Success -> Result.Success(resultDto.data.toLoginResponse())
                        is Result.Error -> Result.Error(resultDto.error)
                    }
                )
            }
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Result<Unit, NetworkError>> {
        return flow {
            emit(authApi.register(registerRequest.toRegisterRequestDto()))
        }
    }

    override suspend fun logout(): Flow<Result<Unit, NetworkError>> {
        return flow {
            emit(authApi.logOut())
        }
    }
}