package com.example.taskly.feature_agenda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskly.feature_agenda.data.local.agenda_event.AgendaEventDao
import com.example.taskly.feature_agenda.data.local.agenda_event.AgendaEventEntity
import com.example.taskly.feature_agenda.data.local.agenda_event.AttendeeEntity
import com.example.taskly.feature_agenda.data.local.agenda_event.PhotoEntity
import com.example.taskly.feature_agenda.data.local.agenda_reminder.AgendaReminderDao
import com.example.taskly.feature_agenda.data.local.agenda_reminder.AgendaReminderEntity
import com.example.taskly.feature_agenda.data.local.agenda_task.AgendaTaskDao
import com.example.taskly.feature_agenda.domain.model.AgendaTask

@Database(
    entities = [AgendaTask::class, AgendaEventEntity::class, AttendeeEntity::class, PhotoEntity::class, AgendaReminderEntity::class],
    version = 1
)
abstract class AgendaDatabase : RoomDatabase() {
    abstract fun agendaTaskDao(): AgendaTaskDao
    abstract fun agendaEventDao(): AgendaEventDao
    abstract fun agendaReminderDao(): AgendaReminderDao
}