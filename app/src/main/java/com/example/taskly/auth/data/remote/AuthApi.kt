package com.example.taskly.auth.data.remote

import com.example.taskly.auth.data.remote.dto.LoginRequest
import com.example.taskly.auth.data.remote.dto.LoginResponse
import com.example.taskly.auth.data.remote.dto.RegisterRequest

interface AuthApi {
    suspend fun login(loginRequest: LoginRequest): LoginResponse?

    suspend fun register(registerRequest: RegisterRequest)

    suspend fun logOut()
}