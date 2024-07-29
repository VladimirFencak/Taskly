package com.example.taskly.core.data.remote

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_auth.data.remote.dto.ErrorMessageDto
import com.example.taskly.feature_auth.data.remote.dto.toErrorString
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerializationException
import java.io.IOException

object NetworkExceptionHandler {
    suspend fun <T> handleNetworkException(exception: Exception): Result<T, NetworkError> {
        return when (exception) {
            is ClientRequestException -> Result.Error(getErrorType(exception.response))
            is ServerResponseException -> Result.Error(getErrorType(exception.response))
            is ConnectTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            is IOException -> Result.Error(NetworkError.NO_INTERNET)
            is SerializationException -> Result.Error(NetworkError.INTERNAL_SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    suspend fun getErrorType(response: HttpResponse): NetworkError {
        return try {
            NetworkError.RECEIVED_SERVER_ERROR_MESSAGE.apply {
                this.errorMessage = (response.body() as ErrorMessageDto).toErrorString()
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