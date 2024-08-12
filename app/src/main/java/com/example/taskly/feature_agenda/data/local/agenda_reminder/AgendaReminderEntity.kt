package com.example.taskly.feature_agenda.data.local.agenda_reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agenda_reminder")
data class AgendaReminderEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long
)