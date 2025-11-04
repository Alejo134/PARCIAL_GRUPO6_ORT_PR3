package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.OceanBlue

/**
 * Un componente de UI "tonto" que muestra información financiera y una barra de progreso.
 * No sabe de dónde vienen los datos, solo los recibe y los muestra.
 *
 * @param totalBalance El valor del balance total a mostrar.
 * @param totalExpense El valor del gasto total a mostrar.
 * @param progressText El texto a mostrar junto a la barra de progreso.
 * @param progressValue El valor actual para la barra de progreso.
 */
@Composable
fun TopPageFinancial_ProgressBar(
    totalBalance: Double,
    totalExpense: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FinancialInfoCard(
                title = "Total Balance",
                value = totalBalance, // Usa el parámetro
                iconResId = R.drawable.totalbalance,
                valueColor = Honeydew,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(LightGreen)
            )

            FinancialInfoCard(
                title = "Total Expense",
                value = totalExpense, // Usa el parámetro
                iconResId = R.drawable.totalexpense,
                valueColor = OceanBlue,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(3.dp))

        ProgressBar(
            categoryName = "30% of your expenses, looks good.",
            currentValue = 20000.0,
            modifier = Modifier.padding(horizontal = 16.dp),
            colorProgressBar = Honeydew,
            colorIcon = FenceGreen
        )

        Spacer(modifier = Modifier.height(25.dp))

    }
}

