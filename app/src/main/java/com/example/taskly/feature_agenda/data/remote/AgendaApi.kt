package com.example.taskly.feature_agenda.data.remote

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import com.example.taskly.feature_agenda.domain.model.AgendaTask

interface AgendaApi {
    suspend fun getAgenda(agendaRequest: AgendaRequest): Result<AgendaResponse, NetworkError>
    suspend fun createTask(agendaTask: AgendaTask): Result<AgendaTask, NetworkError>
    suspend fun getTask(taskId: String): Result<AgendaTask, NetworkError>
    suspend fun updateTask(agendaTask: AgendaTask): Result<AgendaTask, NetworkError>
    suspend fun deleteTask(taskId: String): Result<Unit, NetworkError>
}