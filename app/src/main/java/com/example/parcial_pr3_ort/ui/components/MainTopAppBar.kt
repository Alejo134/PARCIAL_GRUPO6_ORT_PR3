package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.FenceGreen

@Composable
fun MainTopAppBar(
    currentRoute: String,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNotificationClick: () -> Unit
) {

    if (currentRoute != AppRoutes.HOME) {
        // --- DISEÑO ESTÁNDAR PARA OTRAS PANTALLAS ---
        StandardTopAppBar(
            title = getTitleForRoute(currentRoute),
            canNavigateBack = canNavigateBack,
            onNavigateBack = onNavigateBack,
            onNotificationClick = onNotificationClick
        )
    } else {
        // --- DISEÑO PERSONALIZADO PARA LA HOMESCREEN ---
        HomeTopAppBar(
            title = getTitleForRoute(currentRoute),
            onNotificationClick = onNotificationClick
        )
    }
}

// Composable para la barra de la HomeScreen (el código que ya tenías)
@Composable
private fun HomeTopAppBar(title: String, onNotificationClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 20.dp)
            .padding(top = 50.dp)
    ) {
        Text(
            text = title,
            color = FenceGreen,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 13.sp,
            maxLines = 2,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 22.dp)
        )
        IconButton(
            onClick = onNotificationClick,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-16).dp)
                .background(Color.White, CircleShape)
                .size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.campana),
                contentDescription = "Notifications",
                tint = FenceGreen,
                modifier = Modifier.size(17.dp)
            )
        }
    }
}

// Composable NUEVO para la barra estándar (con título centrado y flecha)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StandardTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit,
    onNotificationClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            // El icono de la derecha se mantiene
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier
                    .offset(x = (-24).dp, y = 7.dp)
                    .background(Color.White, CircleShape)
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.campana),
                    contentDescription = "Notifications",
                    tint = FenceGreen,
                    modifier = Modifier.size(17.dp)
                )
            }
            // Añadimos un espaciador para que el icono no esté pegado al borde
            Spacer(modifier = Modifier.width(8.dp))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent // Mantenemos la transparencia
        )
    )
}

// Función de ayuda para no repetir la lógica del título
private fun getTitleForRoute(route: String): String {
    return when (route) {
        AppRoutes.HOME -> "Hi, Welcome Back\nGood Morning"
        AppRoutes.NOTIFICATIONS -> "Notifications"
        AppRoutes.CATEGORIES -> "Categories"
        AppRoutes.ADD_EXPENSES -> "Add Expenses"
        AppRoutes.GIFTS_CATEGORY -> "Gifts"
        AppRoutes.RENT_CATEGORY -> "Rent"
        AppRoutes.FOOD_CATEGORY -> "Food"
        AppRoutes.TRANSPORT_CATEGORY -> "Transport"
        AppRoutes.MEDICINE_CATEGORY -> "Medicine"
        AppRoutes.GROCERIES_CATEGORY -> "Groceries"
        AppRoutes.SAVINGS_CATEGORY -> "Savings"
        AppRoutes.ENTERTAINMENT_CATEGORY -> "Entertainment"
        AppRoutes.TRAVEL_CATEGORY -> "Travel"
        //AppRoutes.ACCOUNT_BALANCE -> "Account Balance"
        //AppRoutes.PROFILE -> "Profile"
        //AppRoutes.TRANSACTION -> "TRANSACTION"
        AppRoutes.ADD_SAVINGS -> "Add Savings"
        AppRoutes.NEW_HOUSE_CATEGORY -> "New House"
        AppRoutes.CAR_CATEGORY -> "Car"
        AppRoutes.WEDDING_CATEGORY -> "Wedding"

        else -> "My Account"
    }
}
