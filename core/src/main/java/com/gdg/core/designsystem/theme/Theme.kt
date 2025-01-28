package com.gdg.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.gdg.core.extension.SetNavigationBarColor
import com.gdg.core.extension.SetStatusBarColor

private val DarkColorScheme = darkColorScheme(
    primary = Green700,
    background = White
)

private val LightColorScheme = lightColorScheme(
    primary = Green700,
    background = White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val LocalCrowdZeroTypography = staticCompositionLocalOf<CrowdZeroTypography> {
    error("CrowdZeroTypography not provided")
}

private val LocalCrowdZeroColors = staticCompositionLocalOf<CrowdZeroColors> {
    error("CrowdZeroColors not provided")
}

object CrowdZeroTheme {
    val typography: CrowdZeroTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCrowdZeroTypography.current

    val colors: CrowdZeroColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCrowdZeroColors.current
}

@Composable
fun ProvideCrowdZeroColorsAndTypography(
    typography: CrowdZeroTypography,
    colors: CrowdZeroColors,
    content: @Composable () -> Unit
) {
    val provideTypography = remember { typography.copy() }
    provideTypography.update(typography)

    val provideColors = remember { colors.copy() }
    provideColors.update(colors)

    CompositionLocalProvider(
        LocalCrowdZeroTypography provides provideTypography,
        LocalCrowdZeroColors provides provideColors,
        content = content
    )
}

@Composable
fun CrowdZeroAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val typography = crowdZeroTypography()
    val colors = crowdZeroColors()
    SetStatusBarColor(color = colors.white)
    SetNavigationBarColor(color = colors.white)
    ProvideCrowdZeroColorsAndTypography(
        typography = typography,
        colors = colors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
