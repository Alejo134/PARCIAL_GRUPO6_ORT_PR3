package com.example.parcial_pr3_ort.ui.screens.launch

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {

    LaunchedEffect(key1 = true) {
        delay(2000L)

        navController.navigate(AppRoutes.ONBOARDING) {
            popUpTo(AppRoutes.LAUNCH) {
                inclusive = true
            }
        }
    }

    val view = LocalView.current
    val splashScreenColor = CaribbeanGreen

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }

    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(splashScreenColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.launch_vector),
                contentDescription = stringResource(id = R.string.brand_name),
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.brand_name),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 55.sp
            )
        }
    }
}
