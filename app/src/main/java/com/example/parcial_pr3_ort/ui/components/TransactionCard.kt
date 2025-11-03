package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun TransactionCard(
    // --- PARÁMETROS REFINADOS SEGÚN LA API Y LA UI ---
    subtype: String,        // "salary", "grocery", etc.
    date: String,           // La fecha de la transacción.
    amount: Double,
    type: String,           // "income" o "expense". Se usa para la lógica, no se muestra.
    modifier: Modifier = Modifier
) {
    // --- LÓGICA INTERNA DEL COMPONENTE ---

    // 1. Lógica del Ícono: Se decide basado en el `subtype`.
    val iconResId = when (subtype.lowercase()) {
        "food" -> R.drawable.foodcategory
        "clothes" -> R.drawable.clothescategory
        "savings" -> R.drawable.categorysalary
        // Añade más casos para otros subtipos de tu API.
        else -> R.drawable.categorysalary // Ícono por defecto si no se encuentra coincidencia.
    }

    // 2. Lógica del Monto: Se formatea basado en el `type`.
    val formattedAmount = "${if (type == "expense") "-" else ""}$${"%,.2f".format(amount)}"
    val amountColor = if (type == "expense") OceanBlue else FenceGreen

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // --- SECCIÓN IZQUIERDA: ÍCONO Y TEXTOS PRINCIPALES ---
        Row(
            modifier = Modifier.width(160.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = subtype,
                modifier = Modifier.width(57.dp).height(53.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                // El texto principal ahora es el `subtype` capitalizado.
                Text(
                    text = subtype.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Bold,
                    color = FenceGreen,
                    fontSize = 16.sp
                )
                // El texto secundario ahora es la `date`.
                Text(
                    text = date,
                    color = OceanBlue,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        VerticalDivider(modifier = Modifier.height(35.dp))

        // --- SECCIÓN CENTRAL: TAMBIÉN USA EL SUBTYPE ---
        Box(
            modifier = Modifier.width(80.dp),
            contentAlignment = Alignment.Center
        ) {
            // El texto del medio también es el `subtype` capitalizado.
            Text(
                text = subtype.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge,
                color = FenceGreen,
                fontSize = 14.sp
            )
        }

        VerticalDivider(modifier = Modifier.height(35.dp))

        Spacer(modifier = Modifier.width(5.dp))


        // --- SECCIÓN DERECHA: MONTO FORMATEADO ---
        Box(
            modifier = Modifier.weight(1f).width(40.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = formattedAmount,
                color = amountColor, // Color dinámico según el `type`.
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun VerticalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxHeight(0.6f)
            .width(1.dp)
            .background(CaribbeanGreen.copy(alpha = 0.5f))
    )
}

@Preview(showBackground = true)
@Composable
fun ExpensesCardPreview() {
    PARCIALPR3ORTTheme {
        Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Ejemplo de un GASTO ("expense")
                TransactionCard(
                    subtype = "Grocery",
                    date = "18:30 - April 30",
                    amount = 150.75,
                    type = "expense"
                )

                // Ejemplo de un INGRESO ("income")
                TransactionCard(
                    subtype = "Salary",
                    date = "09:00 - May 01",
                    amount = 4000.00,
                    type = "income"
                )
            }
        }
    }
}
