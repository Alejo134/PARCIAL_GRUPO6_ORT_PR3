package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.FinancialInfoCard
import com.example.parcial_pr3_ort.ui.components.GoalsCard
import com.example.parcial_pr3_ort.ui.components.PeriodSelector
import com.example.parcial_pr3_ort.ui.components.ProgressBar
import com.example.parcial_pr3_ort.ui.components.TimePeriod
import com.example.parcial_pr3_ort.ui.components.TransactionCard
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            UserAccountRepository(RetrofitClient.api)
        )
    )

    val totalBalance by homeViewModel.totalBalance
    val totalExpense by homeViewModel.totalExpense
    val isLoading by homeViewModel.isLoading
    val transactions by homeViewModel.transactions
    val errorMessage by homeViewModel.errorMessage

    var selectedPeriod by remember { mutableStateOf(TimePeriod.Weekly) }


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 2. Fila superior con las dos tarjetas financieras
            Row(
                // 2.1 APLICAMOS EL PADDING HORIZONTAL AQUÍ
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tarjeta Izquierda (Balance)
                FinancialInfoCard(
                    title = "Total Balance",
                    value = totalBalance,
                    iconResId = R.drawable.totalbalance,
                    valueColor = Honeydew,
                    modifier = Modifier.weight(1f)
                )

                // 3. Línea divisoria vertical
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(60.dp)
                        .background(LightGreen)
                )

                // Tarjeta Derecha (Gasto)
                FinancialInfoCard(
                    title = "Total Expense",
                    value = totalExpense,
                    iconResId = R.drawable.totalexpense,
                    valueColor = OceanBlue,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(3.dp))

            // 4. Barra de progreso con los parámetros especificados
            ProgressBar(
                categoryName = "30% of your expenses, looks good.",
                currentValue = 20000.0,
                colorProgressBar = Honeydew,
                // 2.2 APLICAMOS EL PADDING HORIZONTAL TAMBIÉN AQUÍ
                modifier = Modifier.padding(horizontal = 16.dp),
                colorIcon = FenceGreen,
            )

            Spacer(modifier = Modifier.height(55.dp))

            // 5. Contenedor inferior sin padding
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
                // --- CONTENIDO DENTRO DEL RECTÁNGULO BLANCO ---
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 24.dp,
                            start = 16.dp,
                            end = 16.dp
                        ), // Padding para el contenido interno
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 6. Tarjeta de Metas (GoalsCard)
                    GoalsCard()

                    Spacer(modifier = Modifier.height(24.dp))

                    // 7. Selector de Período
                    PeriodSelector(
                        currentPeriod = selectedPeriod,
                        onPeriodSelected = { newPeriod -> selectedPeriod = newPeriod }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // --- PASO 3: RENDERIZAR LA LISTA DE TRANSACCIONES ---
                    // Controlamos los 3 posibles estados: Cargando, Error o Éxito.
                    when {
                        isLoading -> {
                            // Muestra un spinner mientras los datos cargan.
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                        errorMessage != null -> {
                            // Muestra un mensaje de error si la llamada a la API falló.
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(text = errorMessage!!)
                            }
                        }
                        else -> {
                            // Si todo fue bien, muestra la lista de transacciones.
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            ) {
                                items(transactions) { transaction ->
                                    TransactionCard(
                                        subtype = transaction.subtype,
                                        date = transaction.date,
                                        amount = transaction.amount,
                                        type = transaction.type
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }




// --- Preview para visualizar el resultado ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PARCIALPR3ORTTheme {
        Surface {
            HomeScreen()
        }
    }
}
