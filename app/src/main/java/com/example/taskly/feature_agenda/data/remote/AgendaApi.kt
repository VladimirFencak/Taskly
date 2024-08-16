package com.example.taskly.feature_agenda.data.remote

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.data.remote.dto.AgendaResponseDto
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaTask

interface AgendaApi {
    suspend fun getAgenda(agendaRequest: AgendaRequest): Result<AgendaResponseDto, NetworkError>
    suspend fun getFullAgenda(): Result<AgendaResponseDto, NetworkError>
    suspend fun createTask(agendaTask: AgendaTask): Result<Unit, NetworkError>
    suspend fun getTask(taskId: String): Result<AgendaTask, NetworkError>
    suspend fun updateTask(agendaTask: AgendaTask): Result<Unit, NetworkError>
    suspend fun deleteTask(taskId: String): Result<Unit, NetworkError>
}