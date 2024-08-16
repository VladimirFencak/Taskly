package com.example.taskly.core.domain.errors

sealed interface Result<out D, out E : ErrorDefault> {
    data class Success<out D, out E : ErrorDefault>(val data: D) : Result<D, E>
    data class Error<out D, out E : ErrorDefault>(val error: E, val data: D? = null) : Result<D, E>
}

fun <D, E : ErrorDefault, R> Result<D, E>.map(transform: (D) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.Success(transform(this.data))
        is Result.Error -> Result.Error(this.error)
    }
}