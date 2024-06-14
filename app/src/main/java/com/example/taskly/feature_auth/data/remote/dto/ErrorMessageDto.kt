package com.example.taskly.feature_auth.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessageDto(
    val message: String
)

fun ErrorMessageDto.toErrorString() = this.message