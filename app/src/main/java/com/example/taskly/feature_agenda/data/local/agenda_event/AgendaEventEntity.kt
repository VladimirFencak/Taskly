package com.example.taskly.feature_agenda.data.local.agenda_event

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "agenda_event")
data class AgendaEventEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean
)

@Entity(
    tableName = "attendee",
    foreignKeys = [ForeignKey(
        entity = AgendaEventEntity::class,
        parentColumns = ["id"],
        childColumns = ["eventId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AttendeeEntity(
    @PrimaryKey val email: String,
    val fullName: String,
    val userId: String,
    val eventId: String, // Foreign key to AgendaEvent
    val isGoing: Boolean,
    val remindAt: Long
)

@Entity(
    tableName = "photo",
    foreignKeys = [ForeignKey(
        entity = AgendaEventEntity::class,
        parentColumns = ["id"],
        childColumns = ["eventId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PhotoEntity(
    @PrimaryKey val key: String,
    val url: String,
    val eventId: String // Foreign key to AgendaEvent
)