package com.example.taskly.feature_agenda.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AgendaResponseDto(
    val events: List<AgendaEventDto>,
    val tasks: List<AgendaEventDto>,
    val reminders: List<AgendaReminderDto>
)
