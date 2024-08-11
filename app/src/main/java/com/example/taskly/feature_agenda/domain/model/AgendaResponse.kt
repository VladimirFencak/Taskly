package com.example.taskly.feature_agenda.domain.model

data class AgendaResponse(
    val events: List<AgendaEvent>,
    val tasks: List<AgendaTask>,
    val response: List<AgendaReminder>,
)