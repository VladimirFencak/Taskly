package com.example.taskly.feature_auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskly.R

@Composable
fun PwdInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    var isPasswordVisible = remember { false }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = if (isPasswordVisible) {
                        painterResource(id = R.drawable.visible_pwd)
                    } else {
                        painterResource(id = R.drawable.hidden_pwd)
                    },
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Preview
@Composable
private fun PwdInputPreview() {
    val password = remember { mutableStateOf("") }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PwdInput(
            value = password.value,
            onValueChange = { newPassword -> password.value = newPassword },
            label = "EnterPassword",
        )
    }
}