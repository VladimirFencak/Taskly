package com.example.taskly.feature_auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskly.R
import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.presentation.components.PwdInput
import com.example.taskly.feature_auth.presentation.components.TextInput
import com.example.taskly.ui.components.TButton

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.welcome_back),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
        )

        TextInput(
            value = state.loginName,
            onValueChange = { viewModel.onEvent(LoginEvent.OnLoginNameChange(it)) },
            label = stringResource(R.string.email_address)
        )

        PwdInput(
            value = state.loginPwd,
            onValueChange = { viewModel.onEvent(LoginEvent.OnLoginPwdChange(it)) },
            label = stringResource(R.string.password),
        )

        TButton(
            text = stringResource(R.string.login),
            onClick = { viewModel.onEvent(LoginEvent.Login) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.dont_have_account),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Text(
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Blue,
            modifier = Modifier.clickable { /* Handle sign up click */ }
        )
    }

    val message = when (state.error) {
        NetworkError.RECEIVED_SERVER_ERROR_MESSAGE -> state.error.errorMessage ?: stringResource(R.string.error_unknown)
        NetworkError.REQUEST_TIMEOUT -> stringResource(R.string.error_request_timeout)
        NetworkError.NO_INTERNET -> stringResource(R.string.error_no_internet)
        NetworkError.UNAUTHORIZED -> stringResource(R.string.error_unauthorized)
        NetworkError.FORBIDDEN -> stringResource(R.string.error_forbidden)
        NetworkError.NOT_FOUND -> stringResource(R.string.error_not_found)
        NetworkError.INTERNAL_SERVER_ERROR -> stringResource(R.string.error_internal_server)
        NetworkError.UNKNOWN_ERROR -> stringResource(R.string.error_unknown)
        null -> null
    }
    LaunchedEffect(state.error) {
        message?.let {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}
