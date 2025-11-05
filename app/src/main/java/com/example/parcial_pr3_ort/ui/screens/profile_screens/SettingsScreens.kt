package com.example.parcial_pr3_ort.ui.screens.profile_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreens(navController: NavController) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    topStart = 60.dp,
                    topEnd = 60.dp
                )
            )
            .background(Honeydew)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            SettingsButton(
                text = "Notification Settings",
                icon = painterResource(id = R.drawable.notificationsetting),
                onClick = {navController.navigate(AppRoutes.NOTIFICATION_SETTINGS)}
            )
            SettingsButton(
                text = "Password Settings",
                icon = painterResource(id = R.drawable.passwordsetting),
                onClick = {navController.navigate(AppRoutes.PASSWORD_SETTINGS)}
            )
            SettingsButton(
                text = "Delete Account",
                icon = painterResource(id = R.drawable.deleteaccount),
                onClick = {navController.navigate(AppRoutes.DELETE_ACCOUNT)}
            )

        }
    }
}





@Composable
fun SettingsButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(35.dp),
            tint = Color.Unspecified
        )

        // 2. Texto
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Ir a",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

// --- Preview para visualizar el componente de forma aislada ---
@Preview(showBackground = true)
@Composable
private fun SettingsButtonPreview() {
    PARCIALPR3ORTTheme {
        SettingsButton(
            text = "Notificaciones",
            icon = painterResource(id = R.drawable.notificationsetting), // Usamos un icono de ejemplo
            onClick = {}
        )
    }
}