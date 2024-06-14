package com.example.taskly.feature_auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskly.R
import com.example.taskly.feature_auth.domain.errors.NetworkError
import com.example.taskly.feature_auth.presentation.components.PwdInput
import com.example.taskly.feature_auth.presentation.components.TextInput
import com.example.taskly.ui.Dimensions
import com.example.taskly.ui.components.RoundedTopBar
import com.example.taskly.ui.components.TButton

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                })
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {

        RoundedTopBar(
            title = stringResource(id = R.string.create_account)
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextInput(
            modifier = Modifier
                .padding(start = Dimensions.padding, end = Dimensions.padding),
            value = state.email,
            onValueChange = { viewModel.onEvent(RegisterEvent.OnEmailChange(it)) },
            label = stringResource(R.string.email_address),
            trailingIcon = {
                if (state.isValidEmail) {
                    Icon(
                        painter = painterResource(id = R.drawable.ok_check),
                        contentDescription = null,
                        tint = Color.Green
                    )
                }
            }
        )

        TextInput(
            modifier = Modifier
                .padding(start = Dimensions.padding, end = Dimensions.padding),
            value = state.name,
            onValueChange = { viewModel.onEvent(RegisterEvent.OnNameChange(it)) },
            label = stringResource(R.string.name),
            trailingIcon = {
                if (state.isValidName) {
                    Icon(
                        painter = painterResource(id = R.drawable.ok_check),
                        contentDescription = null,
                        tint = Color.Green
                    )
                }
            }
        )

        PwdInput(
            modifier = Modifier
                .padding(start = Dimensions.padding, end = Dimensions.padding),
            value = state.pwd,
            onValueChange = { viewModel.onEvent(RegisterEvent.OnPwdChange(it)) },
            label = stringResource(R.string.password),
        )

        Spacer(modifier = Modifier.height(50.dp))

        TButton(
            modifier = Modifier
                .padding(Dimensions.padding),
            text = stringResource(R.string.get_started),
            onClick = { viewModel.onEvent(RegisterEvent.Register) }
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