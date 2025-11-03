package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.NotificationItem
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun NotificationsScreen() {
    // LazyColumn es la mejor forma de mostrar listas. Es eficiente.
    Box(
        modifier = Modifier
            .fillMaxSize() // Ahora ocupará todo el espacio restante SIN márgenes
            .clip(
                RoundedCornerShape(
                    topStart = 60.dp,
                    topEnd = 60.dp
                )
            )
            .background(Honeydew)
    ) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp), // Espacio entre cada item
        horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
    ) {
        // Aquí creamos una lista estática de notificaciones para el ejemplo.
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }

        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
        item {
            NotificationItem(
                iconResId = R.drawable.campana, // Usamos la campana como ejemplo
                title = "Subscription Reminder",
                message = "We recommend that you be more attentive to your finances.",
                timestamp = "17:00 - April 24",
                transaction = "Groceries |  Pantry  |  -$100,00",
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(320.dp), // Ancho fijo como pediste
                thickness = 1.dp,
                color = CaribbeanGreen // Color personalizado
            )
        }
    }
}
}

// Preview para ver la pantalla completa
@Preview(showSystemUi = true)
@Composable
fun NotificationsScreenPreview() {
    PARCIALPR3ORTTheme {
        // Envolvemos el preview en un Scaffold para que se parezca más a la app real
        androidx.compose.material3.Scaffold { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NotificationsScreen()
            }
        }
    }
}
