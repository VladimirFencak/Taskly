package com.example.taskly.feature_agenda.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AgendaReminderDto(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
)
