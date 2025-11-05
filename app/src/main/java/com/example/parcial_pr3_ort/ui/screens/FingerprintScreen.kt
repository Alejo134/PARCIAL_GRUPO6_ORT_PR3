package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.LightGreen

@Composable
fun FingerprintScreen(
    onJohnFingerprintClick: () -> Unit = {},
    onAddFingerprintClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main content container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .background(LightGreen, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Fingerprint",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // John Fingerprint item
                FingerprintMenuItem(
                    icon = R.drawable.fingerprint_icon,
                    text = "John Fingerprint",
                    iconBackgroundColor = Color(0xFF6BA5FF),
                    onClick = onJohnFingerprintClick
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Add A Fingerprint item
                FingerprintMenuItem(
                    icon = null,
                    text = "Add A Fingerprint",
                    iconBackgroundColor = Color(0xFF6BA5FF),
                    onClick = onAddFingerprintClick,
                    showPlusIcon = true
                )
            }
        }
    }
}

@Composable
private fun FingerprintMenuItem(
    icon: Int?,
    text: String,
    iconBackgroundColor: Color,
    onClick: () -> Unit,
    showPlusIcon: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon circle
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(iconBackgroundColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (showPlusIcon) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            } else if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.Black
        )
    }
}
