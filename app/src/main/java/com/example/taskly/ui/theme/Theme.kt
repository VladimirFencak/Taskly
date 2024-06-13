package com.example.taskly.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkTertiary,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,
    onPrimary = Color.White,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.Black,
    onSurface = Color.White
)

@Composable
fun TasklyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}