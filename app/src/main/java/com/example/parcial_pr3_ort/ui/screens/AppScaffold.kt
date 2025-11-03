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
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.parcial_pr3_ort.ui.components.AppNavigationBar
import com.example.parcial_pr3_ort.ui.components.MainTopAppBar
import com.example.parcial_pr3_ort.ui.screens.categories_screens.SavingsCatScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.CarScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.NewHouseScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.TravelScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.WeddingScreen
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen

object AppRoutes {
    const val HOME = "home"
    const val NOTIFICATIONS = "notifications"
    const val SETTINGS = "settings"
    const val CATEGORIES = "categories"

    const val CATEGORIES_GRAPH = "categories_graph"
    const val GIFTS_CATEGORY = "gifts_category_screen"
    const val RENT_CATEGORY = "rent_category_screen"
    const val FOOD_CATEGORY = "food_category_screen"
    const val TRANSPORT_CATEGORY = "transport_category_screen"
    const val MEDICINE_CATEGORY = "medicine_category_screen"
    const val GROCERIES_CATEGORY = "groceries_category_screen"
    const val SAVINGS_CATEGORY = "savings_category_screen"
    const val ENTERTAINMENT_CATEGORY = "entertainment_category_screen"

    const val TRAVEL_CATEGORY = "travel_category_screen"

    const val NEW_HOUSE_CATEGORY = "new_house_category_screen"

    const val CAR_CATEGORY = "car_category_screen"

    const val WEDDING_CATEGORY = "wedding_category_screen"
    const val ADD_EXPENSES = "add_expenses_screen"
    const val ADD_SAVINGS = "add_savings_screen"

}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppRoutes.HOME
    val canNavigateBack = navController.previousBackStackEntry != null

    Scaffold(
        topBar = {
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
                navController = navController,
                onNavItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
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
                enterTransition = {
                    slideInVertically(initialOffsetY = { it })
                },
                exitTransition = {
                    slideOutVertically(targetOffsetY = { it })
                }
            ) {
                NotificationsScreen()
            }

            navigation(
                startDestination = AppRoutes.CATEGORIES,
                route = AppRoutes.CATEGORIES_GRAPH
            ) {

                composable(AppRoutes.CATEGORIES) {
                    CategoriesScreen(navController = navController)
                }

                composable(AppRoutes.SAVINGS_CATEGORY){ SavingsCatScreen(navController = navController) }
                composable(AppRoutes.FOOD_CATEGORY){ FoodCatScreen(navController = navController) }
                composable(AppRoutes.TRANSPORT_CATEGORY){ TransportCatScreen(navController = navController) }
                composable(AppRoutes.MEDICINE_CATEGORY){ MedicineCatScreen(navController = navController) }
                composable(AppRoutes.GROCERIES_CATEGORY){ GroceriesCatScreen(navController = navController) }
                composable(AppRoutes.RENT_CATEGORY){ RentCatScreen(navController = navController) }
                composable(AppRoutes.GIFTS_CATEGORY){ GiftsCatScreen(navController = navController) }
                composable(AppRoutes.ENTERTAINMENT_CATEGORY){ EntertainmentCatScreen(navController = navController) }


                composable(AppRoutes.ADD_EXPENSES){ AddExpensesScreen(navController = navController)}
                composable(AppRoutes.TRAVEL_CATEGORY){ TravelScreen(navController = navController) }
                composable(AppRoutes.NEW_HOUSE_CATEGORY){ NewHouseScreen(navController = navController) }
                composable(AppRoutes.CAR_CATEGORY){ CarScreen(navController = navController) }
                composable(AppRoutes.WEDDING_CATEGORY){ WeddingScreen(navController = navController) }
                composable(AppRoutes.ADD_SAVINGS){ AddSavingsScreen(navController = navController)}

            }
        }
    }
}


