package br.dev.brunnofdc.utilator.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,

    onPrimary = DarkOnPrimary,
    onSecondary = DarkOnSecondary,
    onTertiary = DarkOnTertiary,

    surface = DarkSecondary,
    background = DarkTertiary,

    onBackground = Color(0xFFFFFBFE),
    onSurface = Color(0xFFFFFBFE),
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    tertiary = LightTertiary,

    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = LightOnPrimary,
    onSecondary = LightOnSecondary,
    onTertiary = LightOnTertiary,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun UtilatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = colorScheme.primary.toArgb()

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}