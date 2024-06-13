package com.example.taskly.feature_auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskly.R
import com.example.taskly.ui.Dimensions

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = isError,
        trailingIcon = {
            if (value.length > 5) Icon(
                painter = painterResource(id = R.drawable.ok_check),
                contentDescription = null,
                tint = Color.Green
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.smallPadding)
    )
}

@Preview
@Composable
private fun TextInputPreview() {
    val text = remember { mutableStateOf("") }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    TextInput(
        value = text.value,
        onValueChange = { newText -> text.value = newText },
        label = "Enter Text",
        isError = false,
    )
}
