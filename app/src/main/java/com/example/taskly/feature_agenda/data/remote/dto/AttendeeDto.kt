package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.Attendee
import kotlinx.serialization.Serializable

@Serializable
data class AttendeeDto(
    val email: String,
    val fullName: String,
    val userId: String,
    val eventId: String,
    val isGoing: Boolean,
    val remindAt: Long
)

fun AttendeeDto.toAttendee() = Attendee(
    email = this.email,
    fullName = this.fullName,
    userId = this.userId,
    eventId = this.eventId,
    isGoing = this.isGoing,
    remindAt = this.remindAt
)

fun Attendee.toAttendeeDto() = AttendeeDto(
    email = this.email,
    fullName = this.fullName,
    userId = this.userId,
    eventId = this.eventId,
    isGoing = this.isGoing,
    remindAt = this.remindAt
)