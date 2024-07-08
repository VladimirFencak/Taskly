package com.example.taskly.feature_agenda.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    val key: String,
    val url: String,
)
