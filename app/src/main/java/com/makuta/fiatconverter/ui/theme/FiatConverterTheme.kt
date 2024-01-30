package com.makuta.fiatconverter.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat

object FiatConverterTheme {

    private val lightTheme = lightColorScheme(
        surface = Color.White,
        background = Color.White
    )
    private val darkTheme = darkColorScheme(
        surface = Color.Black,
        background = Color.Black
    )

    @Composable
    fun Composer(content: @Composable () -> Unit){
        val isDarkTheme = isSystemInDarkTheme()
        val colorScheme = if (!isDarkTheme) {
                lightTheme
            } else {
                darkTheme
            }
        val view = LocalView.current
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.background.toArgb()
        window.navigationBarColor = colorScheme.background.toArgb()
        val winController = WindowCompat.getInsetsController(window,view)
        winController.isAppearanceLightStatusBars = !isDarkTheme
        winController.isAppearanceLightNavigationBars = !isDarkTheme
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography(),
            shapes =  Shapes(),
            content = {
                ProvideTextStyle(
                    value = TextStyle(color = if(isDarkTheme) Color.White else Color.Black),
                    content = content
                )
            }
        )
    }

}