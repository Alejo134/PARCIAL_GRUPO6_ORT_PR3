package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun SecurityScreen(
    onChangePinClick: () -> Unit = {},
    onFingerprintClick: () -> Unit = {},
    onTermsClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Menu container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .background(Honeydew)
                .padding(horizontal = 32.dp, vertical = 40.dp)
        ) {
            Column {
                // Section title
                Text(
                    text = "Security",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = FenceGreen,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Menu items
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    SecurityMenuItem(
                        text = "Change Pin",
                        onClick = onChangePinClick
                    )

                    SecurityMenuItem(
                        text = "Fingerprint",
                        onClick = onFingerprintClick
                    )

                    SecurityMenuItem(
                        text = "Terms And Conditions",
                        onClick = onTermsClick
                    )
                }
            }
        }
    }
}

@Composable
private fun SecurityMenuItem(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Menu text
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = FenceGreen
        )

        // Arrow icon
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Navigate to $text",
            modifier = Modifier.size(24.dp),
            tint = FenceGreen
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SecurityScreenPreview() {
    PARCIALPR3ORTTheme {
        Surface {
            SecurityScreen()
        }
    }
}

