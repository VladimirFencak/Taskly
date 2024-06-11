package com.example.taskly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonColors(Color.Black, Color.White, Color.Green, Color.White)

    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun TButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TButton(text = "TasklyButton") {

        }
    }
}