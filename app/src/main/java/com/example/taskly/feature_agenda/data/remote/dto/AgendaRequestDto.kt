package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.AgendaRequest
import kotlinx.serialization.Serializable

@Serializable
data class AgendaRequestDto(
    val timezone: String,
    val time: Long,
)

fun AgendaRequestDto.toAgendaRequest() = AgendaRequest(
    timezone = this.timezone,
    time = this.time
)

fun AgendaRequest.toAgendaRequestDto() = AgendaRequestDto(
    timezone = this.timezone,
    time = this.time,
)