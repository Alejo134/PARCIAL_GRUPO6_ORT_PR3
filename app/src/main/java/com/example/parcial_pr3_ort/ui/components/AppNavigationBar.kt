package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen


@Composable
fun AppNavigationBar(
    currentRoute: String,
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
            route = "add"
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.transactions),
            route = "add"
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.category),
            route = "add"
        ),
        BottomNavItem(
            icon = painterResource(id = R.drawable.profile),
            route = AppRoutes.SETTINGS
        )
    )

    Box(modifier = Modifier.background(Honeydew)){
    Box(
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el ancho para poder centrar su contenido
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
            bottomNavItems.forEach { navItem ->
                NavigationBarItem(
                    selected = currentRoute == navItem.route,
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