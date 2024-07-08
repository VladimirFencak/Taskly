package com.example.taskly.feature_agenda.data.remote.dto

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