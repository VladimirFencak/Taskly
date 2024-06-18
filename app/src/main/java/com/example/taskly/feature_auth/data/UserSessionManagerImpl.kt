package com.example.taskly.feature_auth.data

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.core.domain.session.UserSessionManager
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class UserSessionManagerImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : UserSessionManager {
    override suspend fun storeJwtToken(token: String) {
        authRepository.storeJwtToken(token)
    }

    override suspend fun getJwtToken(): String? {
        return authRepository.getJwtToken()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return authRepository.authenticate(getJwtToken() ?: "") is Result.Success
    }

    override suspend fun logOut(): Result<Unit, NetworkError> {
        return authRepository.logout()
    }

}