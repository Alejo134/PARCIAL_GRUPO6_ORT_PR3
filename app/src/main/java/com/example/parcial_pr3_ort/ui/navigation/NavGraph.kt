package com.example.parcial_pr3_ort.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.parcial_pr3_ort.ui.components.AppNavigationBar
import com.example.parcial_pr3_ort.ui.components.MainTopAppBar
import com.example.parcial_pr3_ort.ui.screens.AccountBalanceScreen
import com.example.parcial_pr3_ort.ui.screens.AddExpensesScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.AddSavingsScreen
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.screens.CategoriesScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.ChangePinScreen
//import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.EditProfileScreen
import com.example.parcial_pr3_ort.ui.screens.EntertainmentCatScreen
import com.example.parcial_pr3_ort.ui.screens.FoodCatScreen
import com.example.parcial_pr3_ort.ui.screens.GiftsCatScreen
import com.example.parcial_pr3_ort.ui.screens.GroceriesCatScreen
import com.example.parcial_pr3_ort.ui.screens.HomeScreen
import com.example.parcial_pr3_ort.ui.screens.MedicineCatScreen
import com.example.parcial_pr3_ort.ui.screens.NotificationsScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.ProfileScreen
import com.example.parcial_pr3_ort.ui.screens.RentCatScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.SecurityScreen
import com.example.parcial_pr3_ort.ui.screens.TransactionScreen
import com.example.parcial_pr3_ort.ui.screens.TransportCatScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.FingerprintScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.AddFingerprintScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.JhonFingerprintScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.SuccessScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.TermsAndConditionsScreen
import com.example.parcial_pr3_ort.ui.screens.categories_screens.SavingsCatScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.DeleteAccountScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.HelpFaqScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.NotificationSettingsScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.OnlineSupportScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.PasswordSettingsScreen
import com.example.parcial_pr3_ort.ui.screens.profile_screens.SettingsScreens
import com.example.parcial_pr3_ort.ui.screens.profile_screens_2.EditProfileScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.CarScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.NewHouseScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.TravelScreen
import com.example.parcial_pr3_ort.ui.screens.saving_screens.WeddingScreen
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen


fun NavGraphBuilder.mainNavGraph(rootNavController: NavHostController) {
    // Cuando el RootNavHost navega a MAIN_GRAPH, simplemente llama a este Composable.
    composable(route = AppRoutes.MAIN_GRAPH) {
        MainScaffold(rootNavController = rootNavController)
    }
}
@Composable
fun MainScaffold(rootNavController: NavHostController) {
    // Creamos un NUEVO NavController para la navegación INTERNA (Home, Notificaciones, etc.)
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppRoutes.HOME

    Scaffold(
        topBar = {
            MainTopAppBar(
                currentRoute = currentRoute,
                canNavigateBack = mainNavController.previousBackStackEntry != null,
                onNavigateBack = { mainNavController.navigateUp() },
                onNotificationClick = {
                    if (currentRoute != AppRoutes.NOTIFICATIONS) {
                        mainNavController.navigate(AppRoutes.NOTIFICATIONS)
                    }
                }
            )
        },
        bottomBar = {
            AppNavigationBar(
                navController = mainNavController, // Usa el NavController interno
                onNavItemClick = { route ->
                    mainNavController.navigate(route) {
                        popUpTo(mainNavController.graph.startDestinationId) {
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
            navController = mainNavController, // Importante: usa el NavController interno
            startDestination = AppRoutes.HOME,
            modifier = Modifier.padding(innerPadding).background(CaribbeanGreen),

            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth / 1 },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth / 3 },
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            }
        ) {
            composable(AppRoutes.HOME) {
                HomeScreen()
            }
            composable(AppRoutes.NOTIFICATIONS,
                enterTransition = { slideInVertically(initialOffsetY = { it }) },
                exitTransition = { slideOutVertically(targetOffsetY = { -it }) },
                popEnterTransition = { null },
                popExitTransition = { slideOutVertically(targetOffsetY = { it }) }) {
                NotificationsScreen()
            }
            composable(AppRoutes.ACCOUNT_BALANCE) {
                AccountBalanceScreen()
            }

            composable(AppRoutes.TRANSACTION_SCREEN) {
                TransactionScreen()
            }

            composable(AppRoutes.PROFILE) {
                ProfileScreen(mainNavController)
            }

            composable(AppRoutes.EDIT_PROFILE) {
                EditProfileScreen(mainNavController)
            }



            composable(AppRoutes.SECURITY) {
                SecurityScreen(
                    onChangePinClick = { mainNavController.navigate(AppRoutes.CHANGE_PIN) },
                    onFingerprintClick = { mainNavController.navigate(AppRoutes.FINGERPRINT) },
                    onTermsClick = { mainNavController.navigate(AppRoutes.TERMS_AND_CONDITIONS) }
                )
            }

            composable(AppRoutes.CHANGE_PIN) {
                ChangePinScreen(
                    onChangePinClick = {
                        mainNavController.navigate(AppRoutes.PIN_CHANGED_SUCCESS) {
                            popUpTo(AppRoutes.SECURITY) { inclusive = false }
                        }
                    }
                )
            }

            composable(AppRoutes.FINGERPRINT) {
                FingerprintScreen(
                    onJohnFingerprintClick = { mainNavController.navigate(AppRoutes.JHON_FINGERPRINT) },
                    onAddFingerprintClick = { mainNavController.navigate(AppRoutes.ADD_FINGERPRINT) }
                )
            }

            composable(AppRoutes.ADD_FINGERPRINT) {
                AddFingerprintScreen(
                    onUseTouchIdClick = {
                        mainNavController.navigate(AppRoutes.FINGERPRINT_CHANGED_SUCCESS) {
                            popUpTo(AppRoutes.FINGERPRINT) { inclusive = false }
                        }
                    }
                )
            }

            composable(AppRoutes.JHON_FINGERPRINT) {
                JhonFingerprintScreen(
                    onDeleteClick = {
                        mainNavController.navigate(AppRoutes.FINGERPRINT_DELETED_SUCCESS) {
                            popUpTo(AppRoutes.FINGERPRINT) { inclusive = false }
                        }
                    }
                )
            }

            composable(AppRoutes.TERMS_AND_CONDITIONS) {
                TermsAndConditionsScreen(
                    onAcceptClick = {
                        mainNavController.popBackStack()
                    }
                )
            }

            composable(AppRoutes.PIN_CHANGED_SUCCESS) {
                SuccessScreen(
                    message = "Pin Has Been\nChanged Successfully",
                    onNavigateBack = {
                        mainNavController.navigate(AppRoutes.SECURITY) {
                            popUpTo(AppRoutes.SECURITY) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.FINGERPRINT_CHANGED_SUCCESS) {
                SuccessScreen(
                    message = "Fingerprint Has Been\nChanged Successfully",
                    onNavigateBack = {
                        mainNavController.navigate(AppRoutes.FINGERPRINT) {
                            popUpTo(AppRoutes.FINGERPRINT) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.FINGERPRINT_DELETED_SUCCESS) {
                SuccessScreen(
                    message = "The Fingerprint Has\nBeen Successfully\nDeleted.",
                    onNavigateBack = {
                        mainNavController.navigate(AppRoutes.FINGERPRINT) {
                            popUpTo(AppRoutes.FINGERPRINT) { inclusive = true }
                        }
                    }
                )
            }
            composable(AppRoutes.SETTINGS_SCREEN) { SettingsScreens(mainNavController) }
            composable(AppRoutes.NOTIFICATION_SETTINGS) { NotificationSettingsScreen(mainNavController) }
            composable(AppRoutes.PASSWORD_SETTINGS) { PasswordSettingsScreen(rootNavController = rootNavController)}
            composable(AppRoutes.DELETE_ACCOUNT) { DeleteAccountScreen(rootNavController = rootNavController) }
            composable(AppRoutes.HELP_FAQ) { HelpFaqScreen(mainNavController) }
            composable(AppRoutes.ONLINE_SUPPORT) { OnlineSupportScreen(mainNavController) }

            navigation(
                startDestination = AppRoutes.CATEGORIES,
                route = AppRoutes.CATEGORIES_GRAPH
            ) {

                composable(AppRoutes.CATEGORIES) {
                    CategoriesScreen(navController = mainNavController)
                }
                composable(AppRoutes.SAVINGS_CATEGORY) { SavingsCatScreen(mainNavController) }
                composable(AppRoutes.FOOD_CATEGORY) { FoodCatScreen(mainNavController) }
                composable(AppRoutes.TRANSPORT_CATEGORY) { TransportCatScreen(mainNavController) }
                composable(AppRoutes.MEDICINE_CATEGORY) { MedicineCatScreen(mainNavController) }
                composable(AppRoutes.GROCERIES_CATEGORY) { GroceriesCatScreen(mainNavController) }
                composable(AppRoutes.RENT_CATEGORY) { RentCatScreen(mainNavController) }
                composable(AppRoutes.GIFTS_CATEGORY) { GiftsCatScreen(mainNavController) }
                composable(AppRoutes.ENTERTAINMENT_CATEGORY) {
                    EntertainmentCatScreen(
                        mainNavController
                    )
                }


                composable(AppRoutes.ADD_EXPENSES) { AddExpensesScreen(mainNavController) }
                composable(AppRoutes.TRAVEL_CATEGORY) { TravelScreen(mainNavController) }
                composable(AppRoutes.NEW_HOUSE_CATEGORY) { NewHouseScreen(mainNavController) }
                composable(AppRoutes.CAR_CATEGORY) { CarScreen(mainNavController) }
                composable(AppRoutes.WEDDING_CATEGORY) { WeddingScreen(mainNavController) }
                composable(AppRoutes.ADD_SAVINGS) { AddSavingsScreen(mainNavController) }










            }
        }
    }
}
