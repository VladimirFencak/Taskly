package com.example.taskly.core.domain.session

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result

interface UserSessionManager {
    suspend fun storeJwtToken(token: String)
    suspend fun getJwtToken(): String?
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logOut(): Result<Unit, NetworkError>
}