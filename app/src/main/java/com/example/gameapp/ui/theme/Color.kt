package com.example.gameapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Palette rouge
private val RedColorScheme = lightColorScheme(
    primary = Color(0xFFE53935), // Rouge vif
    onPrimary = Color.White,
    secondary = Color(0xFFEF5350),
    onSecondary = Color.White,
    background = Color(0xFFFAFAFA),
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color(0xFFB00020)
)

@Composable
fun GameAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = RedColorScheme,
        typography = Typography,
        content = content
    )
}