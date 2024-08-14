package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.AgendaResponse
import kotlinx.serialization.Serializable

@Serializable
data class AgendaResponseDto(
    val events: List<AgendaEventDto>,
    val tasks: List<AgendaTaskDto>,
    val reminders: List<AgendaReminderDto>
)

fun AgendaResponseDto.toAgendaResponse() = AgendaResponse(
    events = this.events.map { it.toAgendaEvent() },
    tasks = this.tasks.map { it.toAgendaTask() },
    reminders = this.reminders.map { it.toAgendaReminder() }
)
