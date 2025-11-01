package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial_pr3_ort.ui.components.AppNavigationBar
import com.example.parcial_pr3_ort.ui.components.MainTopAppBar
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen

// ... (El objeto AppRoutes se mantiene igual)
object AppRoutes {
    const val HOME = "home"
    const val NOTIFICATIONS = "notifications"
    const val SETTINGS = "settings"
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppRoutes.HOME
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
            // 2. Pasamos los nuevos parámetros a la TopAppBar
            MainTopAppBar(
                currentRoute = currentRoute,
                canNavigateBack = canNavigateBack,
                onNavigateBack = { navController.navigateUp() },
                onNotificationClick = { if (currentRoute != AppRoutes.NOTIFICATIONS) {
                    navController.navigate(AppRoutes.NOTIFICATIONS)
                } }
            )
        },
        bottomBar = {
            AppNavigationBar(
                currentRoute = currentRoute,
                onNavItemClick = { route ->
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        },
        containerColor = CaribbeanGreen
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoutes.HOME) {
                HomeScreen()
            }
            composable(
                route = AppRoutes.NOTIFICATIONS,
                // Animación de ENTRADA: desliza desde abajo hacia arriba
                enterTransition = {
                    slideInVertically(initialOffsetY = { it })
                },
                // Animación de SALIDA: desliza hacia abajo para desaparecer
                exitTransition = {
                    slideOutVertically(targetOffsetY = { it })
                }
            ) {
                NotificationsScreen()
            }
            // Aquí puedes añadir las otras pantallas para probar la navegación
            // composable(AppRoutes.GOALS) { GoalsScreen() }
        }
    }
}
