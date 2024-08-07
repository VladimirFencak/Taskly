package com.example.taskly.feature_auth.presentation.login

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskly.core.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.LoginRequest
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> login(state.value.loginName, state.value.loginPwd)
            is LoginEvent.OnLoginNameChange -> {
                _state.value = _state.value.copy(loginName = event.name)
                validateEmail(event.name)
            }

            is LoginEvent.OnLoginPwdChange -> {
                _state.value = _state.value.copy(loginPwd = event.pwd)
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

    private fun login(loginName: String, loginPwd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            authRepository.login(
                LoginRequest(
                    loginName,
                    loginPwd
                )
            ).also {
                when (it) {
                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false, error = it.error)
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(isLoading = false, error = null, isLoggedIn = true)
                        authRepository.storeJwtToken(it.data.accessToken)
                    }
                }
            }
        }

    }
}