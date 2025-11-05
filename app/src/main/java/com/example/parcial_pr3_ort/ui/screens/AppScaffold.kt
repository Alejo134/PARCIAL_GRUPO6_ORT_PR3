package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.parcial_pr3_ort.ui.navigation.mainNavGraph
import com.example.parcial_pr3_ort.ui.screens.launch.CreateAccountScreen
import com.example.parcial_pr3_ort.ui.screens.launch.ForgotPasswordScreen
import com.example.parcial_pr3_ort.ui.screens.launch.LoginScreen
import com.example.parcial_pr3_ort.ui.screens.launch.NewPasswordScreen
import com.example.parcial_pr3_ort.ui.screens.launch.OnboardingScreen
import com.example.parcial_pr3_ort.ui.screens.launch.PasswordChangedScreen
import com.example.parcial_pr3_ort.ui.screens.launch.PreWelcomeScreen
import com.example.parcial_pr3_ort.ui.screens.launch.SecurityPinScreen
import com.example.parcial_pr3_ort.ui.screens.launch.SplashScreen

object AppRoutes {


    const val AUTH_GRAPH = "auth_graph"
    const val MAIN_GRAPH = "main_graph"

    // --- PANTALLAS DEL GRAFO DE AUTENTICACIÓN ---
    const val LAUNCH = "launch"
    const val SECONDARY_LAUNCH = "secondary_launch"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val FORGOT_PASSWORD = "forgot_password"
    const val CREATE_ACCOUNT = "create_account"
    const val SECURITY_PIN = "security_pin"
    const val NEW_PASSWORD = "new_password"
    const val PASSWORD_CHANGED = "password_changed"

    // --- PANTALLAS DEL GRAFO DEL MAIN ---

    const val HOME = "home"
    const val NOTIFICATIONS = "notifications"
    const val PROFILE = "profile"
    const val CHANGE_PIN = "change_pin"
    const val CATEGORIES = "categories"
    const val ACCOUNT_BALANCE = "account_balance"

    const val TRANSACTION_SCREEN = "transaction_screen"
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

    const val ADD_FINGERPRINT = "add_fingerprint"
    const val JHON_FINGERPRINT = "jhon_fingerprint"
    const val PIN_CHANGED_SUCCESS = "pin_changed_success"
    const val FINGERPRINT_CHANGED_SUCCESS = "fingerprint_changed_success"
    const val FINGERPRINT_DELETED_SUCCESS = "fingerprint_deleted_success"
    // --- PANTALLAS DEL PROFILE ---


    const val EDIT_PROFILE = "edit_profile"

    const val SECURITY = "security"

    const val CHANGUE_PIN = "change_pin"

    const val FINGERPRINT = "fingerprint"

    const val TERMS_AND_CONDITIONS = "terms_and_conditions"

    const val SETTINGS_SCREEN = "settings_screen"

    const val NOTIFICATION_SETTINGS = "notification_settings"

    const val PASSWORD_SETTINGS = "password_settings"

    const val DELETE_ACCOUNT = "delete_account"

    const val HELP_FAQ = "help_faq"

    const val ONLINE_SUPPORT = "online_support"


}

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        startDestination = AppRoutes.LAUNCH, // La primera pantalla del flujo
        route = AppRoutes.AUTH_GRAPH
    ) {
        composable(AppRoutes.LAUNCH) {
            SplashScreen(navController = navController)
        }
        composable(AppRoutes.SECONDARY_LAUNCH) {
            PreWelcomeScreen(navController = navController)
        }
        composable(AppRoutes.ONBOARDING) {
            OnboardingScreen(navController = navController)
        }
        composable(AppRoutes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(AppRoutes.CREATE_ACCOUNT) {
            CreateAccountScreen(navController = navController)
        }
        composable(AppRoutes.FORGOT_PASSWORD) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(AppRoutes.SECURITY_PIN) {
            SecurityPinScreen(navController = navController)
        }
        composable(AppRoutes.NEW_PASSWORD) {
            NewPasswordScreen(navController = navController)
        }
        composable(AppRoutes.PASSWORD_CHANGED) {
            PasswordChangedScreen(navController = navController)
        }
    }
}

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()


    val isUserLoggedIn = false // Set to 'true' to test main flow, 'false' for auth flow
    val startDestination = if (isUserLoggedIn) AppRoutes.MAIN_GRAPH else AppRoutes.AUTH_GRAPH


    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Grafo para el flujo de autenticación (no usa el innerPadding del Scaffold)
        authNavGraph(navController = navController)

        // Grafo para el flujo principal (SÍ usa el innerPadding)
        mainNavGraph(rootNavController = navController)
    }
}
