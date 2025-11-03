package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R // Asegúrate de que este import sea correcto
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Componente Card para mostrar una única transacción.
 * Muestra un icono basado en la categoría, el título, la fecha y el monto.
 *
 * @param transaction El objeto EntityTransaction con los datos a mostrar.
 */
@Composable
fun TransactionCategory(
    transaction: EntityTransaction,
    modifier: Modifier = Modifier
) {
    // 1. Lógica para seleccionar el ícono correcto basado en la categoría
    val iconResId = remember(transaction.category) {
        when (transaction.category) {
            "Food" -> R.drawable.foodcategory
            "Rent" -> R.drawable.rentcategory
            "Gifts" -> R.drawable.giftscategory
            "Transport" -> R.drawable.transportcategory
            "Medicine" -> R.drawable.medicinecategory
            "Groceries" -> R.drawable.groceriescategory
            "Savings" -> R.drawable.savingscategory
            "Entertainment" -> R.drawable.entertainmentcategory
            "Travel" -> R.drawable.travelcategory
            "New House" -> R.drawable.newhousecategory
            "Car" -> R.drawable.carcategory
            "Wedding" -> R.drawable.weddingcategory
            else -> R.drawable.morecategory // Ícono por defecto
        }
    }

    // 2. Lógica para formatear la fecha (timestamp Long) a un formato legible
    val formattedDate = remember(transaction.date) {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        sdf.format(Date(transaction.date))
    }

    // 3. Diseño del componente con una Card
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Fondo transparente para que herede el del Box
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Icono a la izquierda ---
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = transaction.category,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // --- Columna con Título y Fecha ---
            Column(
                modifier = Modifier.weight(1f) // Ocupa el espacio disponible en el medio
            ) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = formattedDate,
                    fontSize = 12.sp,
                    color = OceanBlue
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = String.format("%s$%.2f", if (transaction.amount < 0) "-" else "+", Math.abs(transaction.amount)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = if (transaction.amount < 0) MaterialTheme.colorScheme.error else Color(0xFF008000) // Rojo para gastos, Verde para ingresos
            )
        }
    }
}

/**
 * Preview para visualizar y probar el componente de forma aislada.
 * Esto nos permite diseñarlo sin tener que ejecutar toda la app.
 */
@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
private fun TransactionCategoryPreview() {
    PARCIALPR3ORTTheme {
        // Creamos un objeto de transacción de ejemplo para la preview
        val sampleTransaction = EntityTransaction(
            id = 1,
            title = "Almuerzo con el equipo",
            category = "Food",
            date = System.currentTimeMillis(),
            amount = -25.50,
            message = "Reunión de proyecto en el restaurante"
        )

        TransactionCategory(transaction = sampleTransaction, modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
private fun TransactionCategoryIncomePreview() {
    PARCIALPR3ORTTheme {
        val sampleIncome = EntityTransaction(
            id = 2,
            title = "Devolución de impuestos",
            category = "Savings",
            date = System.currentTimeMillis() - 86400000L * 10, // Hace 10 días
            amount = 350.00,
            message = "Reintegro anual"
        )
        TransactionCategory(transaction = sampleIncome, modifier = Modifier.padding(16.dp))
    }
}
