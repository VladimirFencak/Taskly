package com.example.taskly.feature_agenda.data.remote

import com.example.taskly.core.data.remote.HttpRoutes
import com.example.taskly.core.data.remote.NetworkExceptionHandler.getErrorType
import com.example.taskly.core.data.remote.NetworkExceptionHandler.handleNetworkException
import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.core.domain.session.UserSessionManager
import com.example.taskly.feature_agenda.data.remote.dto.AgendaResponseDto
import com.example.taskly.feature_agenda.data.remote.dto.toAgendaTaskDto
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaTask
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import javax.inject.Inject

class AgendaApiImpl @Inject constructor(
    private val client: HttpClient,
    private val userSessionManager: UserSessionManager
) : AgendaApi {
    override suspend fun getAgenda(agendaRequest: AgendaRequest): Result<AgendaResponseDto, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.AGENDA) {
                url {
                    parameters.append("timezone", agendaRequest.timezone)
                    parameters.append("time", agendaRequest.time.toString())
                }
                contentType(ContentType.Application.Json)
                headers.append(HttpHeaders.Authorization, "Bearer ${userSessionManager.getJwtToken()}")
            }
            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun createTask(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return try {
            val response = client.post(HttpRoutes.TASK) {
                contentType(ContentType.Application.Json)
                setBody(agendaTask.toAgendaTaskDto())
                headers.append(HttpHeaders.Authorization, "Bearer ${userSessionManager.getJwtToken()}")
            }
            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun getTask(taskId: String): Result<AgendaTask, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.TASK) {
                url {
                    parameters.append("taskId", taskId)
                }
                contentType(ContentType.Application.Json)
                headers.append(HttpHeaders.Authorization, "Bearer ${userSessionManager.getJwtToken()}")
            }
            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun updateTask(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return try {
            val response = client.put(HttpRoutes.TASK) {
                contentType(ContentType.Application.Json)
                setBody(agendaTask.toAgendaTaskDto())
                headers.append(HttpHeaders.Authorization, "Bearer ${userSessionManager.getJwtToken()}")
            }
            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun deleteTask(taskId: String): Result<Unit, NetworkError> {
        return try {
            val response = client.delete(HttpRoutes.TASK) {
                url {
                    parameters.append("taskId", taskId)
                }
                contentType(ContentType.Application.Json)
                headers.append(HttpHeaders.Authorization, "Bearer ${userSessionManager.getJwtToken()}")
            }
            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }
}