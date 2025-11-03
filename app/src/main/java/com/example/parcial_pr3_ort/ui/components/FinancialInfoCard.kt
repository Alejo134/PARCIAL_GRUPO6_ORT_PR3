package com.example.parcial_pr3_ort.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun FinancialInfoCard(
    title: String,
    value: Double,
    @DrawableRes iconResId: Int,
    valueColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CaribbeanGreen
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "$title icon",
                    tint = FenceGreen,
                    modifier = Modifier.size(12.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = FenceGreen
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$ ${"%,.2f".format(value)}",
                style = MaterialTheme.typography.headlineSmall,
                color = valueColor
            )
        }
    }
}

// --- Preview para visualizar el componente reutilizado ---
@Preview(showBackground = true)
@Composable
fun FinancialInfoCardPreview() {
    PARCIALPR3ORTTheme {
        Surface(
            color = CaribbeanGreen,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FinancialInfoCard(
                    title = "Total Balance",
                    value = 120500.50,
                    iconResId = R.drawable.totalbalance,
                    valueColor = Honeydew
                )

                FinancialInfoCard(
                    title = "Total Expense",
                    value = 379499.50,
                    iconResId = R.drawable.totalexpense,
                    valueColor = OceanBlue
                )
            }
        }
    }
}

