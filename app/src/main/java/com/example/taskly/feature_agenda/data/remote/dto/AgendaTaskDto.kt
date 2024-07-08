package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.AgendaTask
import kotlinx.serialization.Serializable

@Serializable
data class AgendaTaskDto(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
    val isDone: Boolean
)

fun AgendaTaskDto.toAgendaTask() = AgendaTask(
    id = this.id,
    title = this.title,
    description = this.description,
    time = this.time,
    remindAt = this.remindAt,
    isDone = this.isDone
)

fun AgendaTask.toAgendaTaskDto() = AgendaTaskDto(
    id = this.id,
    title = this.title,
    description = this.description,
    time = this.time,
    remindAt = this.remindAt,
    isDone = this.isDone
)