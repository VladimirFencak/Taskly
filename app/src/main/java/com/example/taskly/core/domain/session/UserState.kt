package com.example.taskly.core.domain.session

sealed class UserState {
    data object Authenticating : UserState()
    data object Authenticated : UserState()
    data object Unauthenticated : UserState()
}