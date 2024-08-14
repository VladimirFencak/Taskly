package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.AgendaReminder
import kotlinx.serialization.Serializable

@Serializable
data class AgendaReminderDto(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
)

fun AgendaReminderDto.toAgendaReminder() = AgendaReminder(
    id = this.id,
    title = this.title,
    description = this.description,
    time = this.time,
    remindAt = this.remindAt
)

fun AgendaReminder.toAgendaReminderDto() = AgendaReminderDto(
    id = this.id,
    title = this.title,
    description = this.description,
    time = this.time,
    remindAt = this.remindAt
)