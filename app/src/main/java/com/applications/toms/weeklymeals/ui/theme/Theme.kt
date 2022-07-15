package com.applications.toms.weeklymeals.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlueGrey200,
    primaryVariant = BlueGrey700,
    secondary = Teal200,
    secondaryVariant = Teal700,
    onSecondary = Color.Black,
    onPrimary = Color.Black,
    surface = Surface,
    onSurface = Color.Black
)

private val LightColorPalette = lightColors(
    primary = BlueGrey200,
    primaryVariant = BlueGrey700,
    secondary = Teal200,
    secondaryVariant = Teal700,
    onSecondary = Color.Black,
    onPrimary = Color.Black,
    surface = Surface,
    onSurface = Color.Black
)

@Composable
fun WeeklyMealsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}