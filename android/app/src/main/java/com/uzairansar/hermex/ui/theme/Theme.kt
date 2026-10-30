package com.uzairansar.hermex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uzairansar.hermex.data.preferences.AppThemeMode
import dev.chrisbanes.haze.rememberHazeState
import androidx.core.view.WindowCompat

private const val HermexRootBackdropKey = "hermex-root-backdrop"

private val LightColors = lightColorScheme(
    primary = Color(0xFFF59E0B),            // OLYMPUS orange
    onPrimary = Color(0xFF0A1628),          // OLYMPUS navy-900
    secondary = Color(0xFF1E3A5F),          // OLYMPUS navy-700
    tertiary = Color(0xFFF59E0B),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0A1628),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0A1628),
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFF1E3A5F),
    outline = Color(0x66F59E0B),
    outlineVariant = Color(0x331E3A5F),
    error = Color(0xFFEF4444),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFF59E0B),            // OLYMPUS orange
    onPrimary = Color(0xFF0A1628),          // navy-900
    secondary = Color(0xFF3B82F6),          // bright accent
    tertiary = Color(0xFFF59E0B),
    background = Color(0xFF0A1628),         // navy-900 base
    onBackground = Color(0xFFF1F5F9),
    surface = Color(0xFF1E3A5F),            // navy-700
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF162238),     // navy-800
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0x66F59E0B),
    outlineVariant = Color(0x3DF59E0B),
    error = Color(0xFFF87171),
)

private val HermexTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp,
    ),
)

private val HermexShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
)

@Composable
fun HermexDarkContent(content: @Composable () -> Unit) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as? Activity)?.window ?: return@SideEffect
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
    }
    MaterialTheme(
        colorScheme = DarkColors,
        typography = HermexTypography,
        shapes = HermexShapes,
    ) {
        CompositionLocalProvider(
            LocalHermexSurfaceTokens provides DarkHermexSurfaceTokens,
            content = content,
        )
    }
}

@Composable
fun HermexTheme(
    themeMode: AppThemeMode = AppThemeMode.System,
    content: @Composable () -> Unit,
) {
    val darkTheme = when (themeMode) {
        AppThemeMode.System -> isSystemInDarkTheme()
        AppThemeMode.Light -> false
        AppThemeMode.Dark -> true
    }
    // Haze's legacy RenderScript path can lag on scrolling screens; Android 12+ uses RenderEffect.
    val hazeState = rememberHazeState(blurEnabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
    val surfaceTokens = if (darkTheme) DarkHermexSurfaceTokens else LightHermexSurfaceTokens
    val view = LocalView.current

    SideEffect {
        val window = (view.context as? Activity)?.window ?: return@SideEffect
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            @Suppress("DEPRECATION")
            window.statusBarColor = Color.Transparent.toArgb()
            @Suppress("DEPRECATION")
            window.navigationBarColor = Color.Transparent.toArgb()
        }
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = !darkTheme
            isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = HermexTypography,
        shapes = HermexShapes,
    ) {
        CompositionLocalProvider(
            LocalHermexHazeState provides hazeState,
            LocalHermexSurfaceTokens provides surfaceTokens,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .hermexHazeSource(key = HermexRootBackdropKey)
                        .background(hermexBackgroundColor()),
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    content = content,
                )
            }
        }
    }
}
