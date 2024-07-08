package com.example.taskly.feature_agenda.domain.model

data class AgendaEvent(
    val id: String,
    val title: String,
    val description: String?,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val attendees: List<Attendee>,
    val photos: List<String>
)