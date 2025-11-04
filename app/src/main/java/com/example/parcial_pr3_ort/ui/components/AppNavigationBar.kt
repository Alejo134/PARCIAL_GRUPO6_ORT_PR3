package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen

@Composable
fun AppNavigationBar(
    navController: NavController,
    onNavItemClick: (String) -> Unit
) {

    data class BottomNavItem(
        val icon: Painter,
        val route: String
    )

    val bottomNavItems = listOf(
        BottomNavItem(
            icon = painterResource(id = R.drawable.home),
            route = AppRoutes.HOME
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.account_balance),
            route = AppRoutes.ACCOUNT_BALANCE
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.transactions),
            route = AppRoutes.TRANSACTION_SCREEN
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.category),
            route = AppRoutes.CATEGORIES_GRAPH
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.profile),
            route = AppRoutes.SETTINGS
        )
    )

    Box(modifier = Modifier.background(Honeydew)){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(topStart = 70.dp, topEnd = 70.dp))
            .background(LightGreen),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            bottomNavItems.forEach { navItem ->
                NavigationBarItem(
                    // 4. LA LÓGICA DE SELECCIÓN DEFINITIVA
                    selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                    onClick = {
                        if (navItem.route != "add") {
                            onNavItemClick(navItem.route)
                        }
                    },
                    icon = {
                        Icon(painter = navItem.icon, contentDescription = "")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = FenceGreen,
                        selectedIconColor = FenceGreen,
                        indicatorColor = CaribbeanGreen
                    )
                )
            }
        }
    }
}
}