package com.example.taskly.feature_agenda.data.remote.dto

import com.example.taskly.feature_agenda.domain.model.Photo
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    val key: String,
    val url: String,
)

fun PhotoDto.toPhoto() = Photo(
    key = this.key,
    url = this.url
)

fun Photo.toPhotoDto() = PhotoDto(
    key = this.key,
    url = this.url
)