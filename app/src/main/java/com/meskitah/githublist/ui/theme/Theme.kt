package com.meskitah.githublist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.meskitah.githublist.core.ui.Blue
import com.meskitah.githublist.core.ui.DarkerBlue
import com.meskitah.githublist.core.ui.DarkerRed
import com.meskitah.githublist.core.ui.DarkerYellow
import com.meskitah.githublist.core.ui.LightBlack
import com.meskitah.githublist.core.ui.Red
import com.meskitah.githublist.core.ui.Yellow

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    secondary = Red,
    tertiary = Yellow,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkerBlue,
    onPrimary = Color.White,
    secondary = DarkerRed,
    tertiary = DarkerYellow,
    background = LightBlack,
)

@Composable
fun GitHubListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}