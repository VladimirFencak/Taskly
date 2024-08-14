package com.example.taskly.feature_agenda.data.local.agenda_task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskly.feature_agenda.domain.model.AgendaTask

@Entity(tableName = "agenda_task")
data class AgendaTaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
    val isDone: Boolean
)

fun AgendaTaskEntity.toAgendaTask() = AgendaTask(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt,
    isDone = isDone
)

fun AgendaTask.toAgendaTaskEntity() = AgendaTaskEntity(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt,
    isDone = isDone
)