package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.AgendaEvent
import kotlinx.serialization.Serializable

@Serializable
data class AgendaEventDto(
    val id: String,
    val title: String,
    val description: String,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val attendees: List<AttendeeDto>,
    val photos: List<PhotoDto>
)

fun AgendaEventDto.toAgendaEvent() = AgendaEvent(
    id = this.id,
    title = this.title,
    description = this.description,
    from = this.from,
    to = this.to,
    remindAt = this.remindAt,
    host = this.host,
    isUserEventCreator = this.isUserEventCreator,
    attendees = this.attendees.map { it.toAttendee() },
    photos = this.photos.map { it.toPhoto() }
)

fun AgendaEvent.toAgendaEventDto() = AgendaEventDto(
    id = this.id,
    title = this.title,
    description = this.description ?: "",
    from = this.from,
    to = this.to,
    remindAt = this.remindAt,
    host = this.host,
    isUserEventCreator = this.isUserEventCreator,
    attendees = this.attendees.map { it.toAttendeeDto() },
    photos = this.photos.map { it.toPhotoDto() }
)