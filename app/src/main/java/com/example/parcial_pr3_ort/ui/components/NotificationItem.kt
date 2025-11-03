package com.example.parcial_pr3_ort.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import com.example.parcial_pr3_ort.ui.theme.Poppins

@Composable
fun NotificationItem(
    @DrawableRes iconResId: Int, // Recurso del ícono (ej: R.drawable.icono)
    title: String,
    message: String,
    transaction: String,
    timestamp: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        // Row principal que contiene todo
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Icono a la izquierda
            Box(
                modifier = Modifier
                    .width(37.dp).height(37.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF00D09E)), // Color de fondo gris claro
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    modifier = Modifier.width(21.dp).height(26.dp),
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 2. Columna con Título y Mensaje
            Column(
                // Este Spacer empuja la fecha hacia la derecha
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Poppins,
                    color = Color.Black,
                )
                Text(
                    text = message,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = Poppins,
                    color = Color.Gray,
                    letterSpacing = 0.sp,
                    lineHeight = 15.sp
                )
                Text(
                    text = transaction,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = Poppins,
                    color = OceanBlue
                )
            }

            Text(
                text = timestamp,
                color = OceanBlue,
                modifier = Modifier.align(Alignment.Bottom),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Poppins
            )
        }
    }
}

// Preview para ver el componente de forma aislada
@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun NotificationItemPreview() {
    PARCIALPR3ORTTheme {
        NotificationItem(
            iconResId = R.drawable.campana, // Usamos la campana como ejemplo
            title = "Subscription Reminder",
            message = "We recommend that you be more attentive to your finances.",
            timestamp = "17:00 - April 24",
            transaction = "Groceries |  Pantry  |  -$100,00",
            modifier = Modifier.padding(16.dp)
        )
    }
}
