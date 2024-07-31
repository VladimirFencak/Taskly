package com.example.taskly.feature_agenda.data.repository

import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.data.remote.AgendaApi
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import com.example.taskly.feature_agenda.domain.model.AgendaTask
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val agendaApi: AgendaApi
) : AgendaRepository {
    override suspend fun getAgenda(agendaRequest: AgendaRequest): Result<AgendaResponse, NetworkError> {
        return agendaApi.getAgenda(agendaRequest)
    }

    override suspend fun createTask(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return agendaApi.createTask(agendaTask)
    }

    override suspend fun getTask(taskId: String): Result<AgendaTask, NetworkError> {
        return agendaApi.getTask(taskId)
    }

    override suspend fun updateTask(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return agendaApi.updateTask(agendaTask)
    }

    override suspend fun deleteTask(taskId: String): Result<Unit, NetworkError> {
        return agendaApi.deleteTask(taskId)
    }
}