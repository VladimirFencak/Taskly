package com.example.taskly.feature_agenda.data.repository

import com.example.taskly.core.domain.errors.ErrorDefault
import com.example.taskly.core.domain.errors.NetworkError
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.core.domain.errors.map
import com.example.taskly.feature_agenda.data.local.agenda_event.AgendaEventDao
import com.example.taskly.feature_agenda.data.local.agenda_event.toAgendaEvent
import com.example.taskly.feature_agenda.data.local.agenda_reminder.AgendaReminderDao
import com.example.taskly.feature_agenda.data.local.agenda_reminder.toAgendaReminder
import com.example.taskly.feature_agenda.data.local.agenda_task.AgendaTaskDao
import com.example.taskly.feature_agenda.data.local.agenda_task.toAgendaTask
import com.example.taskly.feature_agenda.data.local.agenda_task.toAgendaTaskEntity
import com.example.taskly.feature_agenda.data.remote.AgendaApi
import com.example.taskly.feature_agenda.data.remote.dto.toAgendaResponse
import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import com.example.taskly.feature_agenda.domain.model.AgendaTask
import com.example.taskly.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val agendaApi: AgendaApi,
    private val agendaEventDao: AgendaEventDao,
    private val agendaTaskDao: AgendaTaskDao,
    private val agendaReminderDao: AgendaReminderDao
) : AgendaRepository {
    override suspend fun getAgendaRemote(agendaRequest: AgendaRequest): Result<AgendaResponse, NetworkError> {
        return agendaApi.getAgenda(agendaRequest).map { it.toAgendaResponse() }
    }

    override suspend fun getFullAgendaRemote(): Result<AgendaResponse, NetworkError> {
        return agendaApi.getFullAgenda().map { it.toAgendaResponse() }
    }

    override suspend fun createTaskRemote(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return agendaApi.createTask(agendaTask)
    }

    override suspend fun getTaskRemote(taskId: String): Result<AgendaTask, NetworkError> {
        return agendaApi.getTask(taskId)
    }

    override suspend fun updateTaskRemote(agendaTask: AgendaTask): Result<Unit, NetworkError> {
        return agendaApi.updateTask(agendaTask)
    }

    override suspend fun deleteTaskRemote(taskId: String): Result<Unit, NetworkError> {
        return agendaApi.deleteTask(taskId)
    }

    override suspend fun getFullAgendaLocal(): Result<Flow<AgendaResponse>, NetworkError> {
        return try {
            Result.Success(
                combine(
                    agendaEventDao.getAllEventsWithDetails(),
                    agendaTaskDao.getAllTasks(),
                    agendaReminderDao.getAllReminders()
                ) { events, tasks, reminders ->
                    AgendaResponse(
                        events = events.map { it.toAgendaEvent() },
                        tasks = tasks.map { it.toAgendaTask() },
                        reminders = reminders.map { it.toAgendaReminder() }
                    )
                }
            )
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    //    override suspend fun getAgendaLocal(agendaRequest: AgendaRequest): Result<Flow<AgendaResponse>, ErrorDefault> {
    //
    //    }

    override suspend fun insertTasksLocal(tasks: List<AgendaTask>): Result<Unit, ErrorDefault> {
        return try {
            agendaTaskDao.insertTasks(tasks.map { it.toAgendaTaskEntity() })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun createTaskLocal(agendaTask: AgendaTask): Result<Unit, ErrorDefault> {
        return try {
            agendaTaskDao.insertTask(agendaTask.toAgendaTaskEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun getTaskLocal(taskId: String): Result<Flow<AgendaTask?>, ErrorDefault> {
        return try {
            Result.Success(
                agendaTaskDao.getTaskById(taskId).map { it?.toAgendaTask() }
            )
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun updateTaskLocal(agendaTask: AgendaTask): Result<Unit, ErrorDefault> {
        return try {
            agendaTaskDao.insertTask(agendaTask.toAgendaTaskEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    override suspend fun deleteTaskLocal(taskId: String): Result<Unit, ErrorDefault> {
        return try {
            agendaTaskDao.deleteTask(taskId)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }
}