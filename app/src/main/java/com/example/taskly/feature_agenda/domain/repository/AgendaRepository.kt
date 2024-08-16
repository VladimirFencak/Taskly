package com.example.taskly.feature_agenda.domain.repository

import com.example.taskly.core.domain.errors.ErrorDefault
import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import com.example.taskly.feature_agenda.domain.model.AgendaTask
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    suspend fun getAgendaRemote(agendaRequest: AgendaRequest): Result<AgendaResponse, NetworkError>
    suspend fun getFullAgendaRemote(): Result<AgendaResponse, NetworkError>
    suspend fun createTaskRemote(agendaTask: AgendaTask): Result<Unit, NetworkError>
    suspend fun getTaskRemote(taskId: String): Result<AgendaTask, NetworkError>
    suspend fun updateTaskRemote(agendaTask: AgendaTask): Result<Unit, NetworkError>
    suspend fun deleteTaskRemote(taskId: String): Result<Unit, NetworkError>

    suspend fun getFullAgendaLocal(): Result<Flow<AgendaResponse>, ErrorDefault>

    //suspend fun getAgendaLocal(agendaRequest: AgendaRequest): Result<Flow<AgendaResponse>, ErrorDefault>
    suspend fun insertTasksLocal(tasks: List<AgendaTask>): Result<Unit, ErrorDefault>
    suspend fun createTaskLocal(agendaTask: AgendaTask): Result<Unit, ErrorDefault>
    suspend fun getTaskLocal(taskId: String): Result<Flow<AgendaTask?>, ErrorDefault>
    suspend fun updateTaskLocal(agendaTask: AgendaTask): Result<Unit, ErrorDefault>
    suspend fun deleteTaskLocal(taskId: String): Result<Unit, ErrorDefault>
}