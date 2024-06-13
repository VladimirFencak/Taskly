package com.example.taskly.feature_auth.presentation.register

sealed class RegisterEvent {
    data object Register : RegisterEvent()
    data class OnEmailChange(val email: String) : RegisterEvent()
    data class OnNameChange(val name: String) : RegisterEvent()
    data class OnPwdChange(val pwd: String) : RegisterEvent()
}