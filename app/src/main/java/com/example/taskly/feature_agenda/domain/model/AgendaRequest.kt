package com.example.taskly.feature_agenda.domain.model

data class AgendaRequest(
    val timezone: String,
    val time: Long
)