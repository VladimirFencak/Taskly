package com.example.taskly.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskly.core.domain.session.UserSessionManager
import com.example.taskly.core.domain.session.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager
) : ViewModel(

) {
    private val _userState = MutableStateFlow<UserState>(UserState.Authenticating)
    val userState = _userState.asStateFlow()

    init {
        viewModelScope.launch {
            if (userSessionManager.isUserLoggedIn()) {
                _userState.value = UserState.Authenticated
            } else {
                _userState.value = UserState.Unauthenticated
            }
        }
    }
}