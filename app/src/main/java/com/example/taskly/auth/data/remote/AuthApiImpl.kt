package com.example.taskly.auth.data.remote

import com.example.taskly.BuildConfig
import com.example.taskly.auth.data.remote.dto.LoginRequest
import com.example.taskly.auth.data.remote.dto.LoginResponse
import com.example.taskly.auth.data.remote.dto.RegisterRequest
import com.example.taskly.auth.domain.errors.NetworkError
import com.example.taskly.auth.domain.errors.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.SerializationException
import java.io.IOException

class AuthApiImpl(
    private val client: HttpClient
) : AuthApi {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse, NetworkError> {
        return try {
            val response = client.post(HttpRoutes.LOGIN) {
                contentType(ContentType.Application.Json)
                header("x-api-key", BuildConfig.apiKey)
                setBody(loginRequest)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleException(e)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<Unit, NetworkError> {
        return try {
            val response = client.post(HttpRoutes.REGISTER) {
                contentType(ContentType.Application.Json)
                header("x-api-key", BuildConfig.apiKey)
                setBody(registerRequest)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleException(e)
        }
    }

    override suspend fun logOut(): Result<Unit, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.LOGOUT) {
                contentType(ContentType.Application.Json)
                header("x-api-key", BuildConfig.apiKey)
            }

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))

        } catch (e: Exception) {
            handleException(e)
        }
    }

    private suspend fun <T> handleException(exception: Exception): Result<T, NetworkError> {
        return when (exception) {
            is ClientRequestException -> Result.Error(getErrorType(exception.response))
            is ServerResponseException -> Result.Error(getErrorType(exception.response))
            is ConnectTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            is IOException -> Result.Error(NetworkError.NO_INTERNET)
            is SerializationException -> Result.Error(NetworkError.INTERNAL_SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    private suspend fun getErrorType(response: HttpResponse): NetworkError {
        return try {
            NetworkError.RECEIVED_SERVER_ERROR_MESSAGE.apply {
                this.errorMessage = response.body()
            }
        } catch (e: SerializationException) {
            when (response.status) {
                HttpStatusCode.Unauthorized -> NetworkError.UNAUTHORIZED
                HttpStatusCode.Forbidden -> NetworkError.FORBIDDEN
                HttpStatusCode.NotFound -> NetworkError.NOT_FOUND
                HttpStatusCode.InternalServerError -> NetworkError.INTERNAL_SERVER_ERROR
                else -> NetworkError.UNKNOWN_ERROR
            }
        }
    }
}