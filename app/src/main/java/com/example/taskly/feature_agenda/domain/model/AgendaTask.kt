package com.example.taskly.feature_agenda.domain.model

data class AgendaTask(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
    val isDone: Boolean,
)
