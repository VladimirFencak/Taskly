package com.example.taskly.feature_agenda.data.local.agenda_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agenda_task")
data class AgendaTaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
    val isDone: Boolean
)