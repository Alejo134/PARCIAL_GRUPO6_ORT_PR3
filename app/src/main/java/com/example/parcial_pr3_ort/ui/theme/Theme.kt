package com.example.parcial_pr3_ort.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


// --- 1. ESQUEMA DE COLORES PARA EL TEMA CLARO (LIGHT THEME) ---
private val LightColorScheme = lightColorScheme(
    primary = CaribbeanGreen,       // Color principal y más llamativo. Para botones, switches, etc.
    onPrimary = Color.White,          // Color del texto que va SOBRE el color primario.

    secondary = LightGreen,         // Color secundario para acentos.
    onSecondary = Color.Black,        // Texto sobre el color secundario.

    background = Honeydew,          // El color de fondo más común en las pantallas.
    onBackground = Color.Black,       // Color del texto principal que va sobre el fondo.

    surface = Honeydew,             // Color de las superficies como Cards, menús, etc.
    onSurface = Color.Black,          // Color del texto que va sobre las superficies.

    // También puedes definir otros colores como 'error', 'outline', etc.
    // secondaryContainer = LightBlue,
    // onSecondaryContainer = Color.Black,
)

// --- 2. ESQUEMA DE COLORES PARA EL TEMA OSCURO (DARK THEME) ---
private val DarkColorScheme = darkColorScheme(
    primary = FenceGreen,           // Un color primario que resalta bien en fondos oscuros.
    onPrimary = Color.White,          // Texto sobre el primario.

    secondary = Cyprus,              // Color secundario para acentos en modo oscuro.
    onSecondary = Color.White,        // Texto sobre el secundario.

    background = Void,                // El fondo oscuro principal de tus pantallas.
    onBackground = Color.White,       // Texto principal sobre el fondo oscuro.

    surface = Cyprus,                // Superficies como Cards pueden tener un color ligeramente distinto al fondo.
    onSurface = Color.White           // Texto sobre las superficies oscuras.


)

@Composable
fun PARCIALPR3ORTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Forzar 'dynamicColor = false' para usar siempre nuestra paleta personalizada.
    dynamicColor: Boolean = false,
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

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb() // Color de la barra de estado
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Tu archivo Type.kt
        content = content
    )
}
