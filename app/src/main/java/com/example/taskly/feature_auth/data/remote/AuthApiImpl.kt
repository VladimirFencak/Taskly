package com.example.taskly.feature_auth.data.remote

import com.example.taskly.core.data.remote.HttpRoutes
import com.example.taskly.core.data.remote.NetworkExceptionHandler.getErrorType
import com.example.taskly.core.data.remote.NetworkExceptionHandler.handleNetworkException
import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_auth.data.remote.dto.LoginRequestDto
import com.example.taskly.feature_auth.data.remote.dto.LoginResponseDto
import com.example.taskly.feature_auth.data.remote.dto.RegisterRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import javax.inject.Inject

class AuthApiImpl @Inject constructor(
    private val client: HttpClient
) : AuthApi {
    override suspend fun login(loginRequestDto: LoginRequestDto): Result<LoginResponseDto, NetworkError> {
        return try {
            val response = client.post(HttpRoutes.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(loginRequestDto)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun register(registerRequestDto: RegisterRequestDto): Result<Unit, NetworkError> {
        return try {
            val response = client.post(HttpRoutes.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(registerRequestDto)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun logOut(): Result<Unit, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.LOGOUT) {
                contentType(ContentType.Application.Json)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun authenticate(bearerToken: String): Result<Unit, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.AUTHENTICATE) {
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $bearerToken")
                }
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

}