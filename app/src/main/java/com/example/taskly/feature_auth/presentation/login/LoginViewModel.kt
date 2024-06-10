package com.example.taskly.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.domain.errors.Result
import com.example.taskly.feature_auth.domain.model.LoginRequest
import com.example.taskly.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
            }

            is LoginEvent.OnLoginPwdChange -> {
                _state.value = _state.value.copy(loginPwd = event.pwd)
            }

            LoginEvent.SignUp -> {
                //navigate to sign up
            }
        }
    }

    private fun login(loginName: String, loginPwd: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            authRepository.login(
                LoginRequest(
                    loginName,
                    loginPwd
                )
            ).also {
                when (it) {
                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                        _state.value = _state.value.copy(
                            error = when (it.error) {
                                NetworkError.RECEIVED_SERVER_ERROR_MESSAGE -> it.error.errorMessage ?: "unknown error"
                                NetworkError.REQUEST_TIMEOUT -> ""
                                NetworkError.NO_INTERNET -> ""
                                NetworkError.UNAUTHORIZED -> ""
                                NetworkError.FORBIDDEN -> ""
                                NetworkError.NOT_FOUND -> ""
                                NetworkError.INTERNAL_SERVER_ERROR -> ""
                                NetworkError.UNKNOWN_ERROR -> ""
                            }
                        )
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(isLoading = false)
                        //navigate to home screen
                    }
                }
            }
        }

    }
}