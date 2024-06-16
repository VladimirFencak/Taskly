package com.example.taskly.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.taskly.ui.Dimensions
import com.example.taskly.ui.theme.TasklyTheme

@Composable
fun RoundedTopBar(
    modifier: Modifier = Modifier,
    height: Dp = 200.dp,
    topPartColor: Color = MaterialTheme.colorScheme.primary,
    bottomPartColor: Color = MaterialTheme.colorScheme.background,
    title: String = ""
) {
    val cornerRadius = with(LocalDensity.current) { Dimensions.cornerRadius.toPx() }
    Box(
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .drawWithCache {
                val path = Path().apply {
                    addRect(
                        rect = Rect(
                            left = 0f,
                            right = size.width,
                            top = 0f,
                            bottom = size.height
                        )
                    )
                    close()
                }

                onDrawBehind {
                    drawPath(
                        path = path,
                        color = topPartColor
                    )
                }
            }
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.cornerRadius)
            .align(Alignment.BottomCenter)
            .drawWithCache {
                val path = Path().apply {
                    addRoundRect(
                        roundRect = RoundRect(
                            left = 0f,
                            right = size.width,
                            top = 0f,
                            bottom = size.height,
                            topRightCornerRadius = CornerRadius(x = cornerRadius, y = cornerRadius),
                            topLeftCornerRadius = CornerRadius(x = cornerRadius, y = cornerRadius)
                        )
                    )
                    close()
                }
                onDrawBehind {
                    drawPath(
                        path = path,
                        color = bottomPartColor
                    )
                }

            }
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
        )
    }
}

@Preview
@Composable
fun RoundedTopBarPreview() {
    TasklyTheme {
        RoundedTopBar(
            title = "Title"
        )
    }
}
