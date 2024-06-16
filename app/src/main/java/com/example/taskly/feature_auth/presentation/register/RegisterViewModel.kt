package com.example.taskly.feature_auth.presentation.register

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.RegisterRequest
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnEmailChange -> {
                _state.value = _state.value.copy(email = event.email)
                validateEmail(event.email)
            }

            is RegisterEvent.OnNameChange -> {
                validateName(event.name)
                _state.value = _state.value.copy(name = event.name)
            }

            is RegisterEvent.OnPwdChange -> {
                _state.value = _state.value.copy(pwd = event.pwd)
            }

            RegisterEvent.Register -> {
                register(
                    state.value.email,
                    state.value.name,
                    state.value.pwd
                )
            }
        }
    }

    private fun validateEmail(email: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = _state.value.copy(isValidEmail = true)
        } else {
            _state.value = _state.value.copy(isValidEmail = false)
        }
    }

    private fun validateName(name: String) {
        if (name.length in 4..50) {
            _state.value = _state.value.copy(isValidName = true)
        } else {
            _state.value = _state.value.copy(isValidName = false)
        }
    }

    private fun register(email: String, name: String, pwd: String) {
        viewModelScope.launch {
            authRepository.register(
                RegisterRequest(name, email, pwd)
            ).also {
                when (it) {
                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false, error = it.error)
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(isLoading = false, error = null, registered = true)
                    }
                }
            }
        }
    }
}