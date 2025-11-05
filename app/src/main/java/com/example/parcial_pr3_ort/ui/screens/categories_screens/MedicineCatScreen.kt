package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.AppDataBase
import com.example.parcial_pr3_ort.data.TransactionRepository
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.ButtonAddExpenses
import com.example.parcial_pr3_ort.ui.components.TopPageFinancial_ProgressBar
import com.example.parcial_pr3_ort.ui.components.TransactionCategory
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.viewmodel.CategoryDetailViewModel
import com.example.parcial_pr3_ort.viewmodel.CategoryDetailViewModelFactory
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory

@Composable
fun MedicineCatScreen(navController: NavController) {


    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            UserAccountRepository(RetrofitClient.api)
        )
    )

    val context = LocalContext.current
    val viewModel: CategoryDetailViewModel = viewModel(
        factory = CategoryDetailViewModelFactory(
            repository = TransactionRepository(AppDataBase.getDatabase(context).transactionDao()),
            categoryName = "Medicine"
        )
    )

    val totalBalance by homeViewModel.totalBalance
    val totalExpense by homeViewModel.totalExpense
    val groupedTransactions by viewModel.groupedTransactions.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            TopPageFinancial_ProgressBar(
                totalBalance = totalBalance,
                totalExpense = totalExpense,
                modifier = Modifier.padding(top = 16.dp)
            )


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


                if (groupedTransactions.isEmpty()) {
                    // Mensaje si no hay transacciones
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Aún no tienes gastos de comida.", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    // --- 2. IMPLEMENTACIÓN DE LA LISTA CON EL NUEVO COMPONENTE ---
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 32.dp, start = 16.dp, end = 16.dp)
                    ) {

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier
                                        .background(Color.White, CircleShape)
                                        .size(32.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "Calendar",
                                        modifier = Modifier.size(35.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        }

                        groupedTransactions.forEach { (month, transactions) ->
                            item {
                                Text(
                                    text = month,
                                    color = Cyprus,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .padding(bottom = 8.dp)
                                )
                            }
                            items(transactions, key = { it.id }) { transaction ->
                                TransactionCategory(transaction = transaction)
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }

                    ButtonAddExpenses(onClick = {
                        navController.navigate("add_expenses_screen")
                    },
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // <-- La magia para posicionarlo
                            .padding(bottom = 24.dp)         // Espacio desde el borde inferior
                        , text = "Add Expense")

                }

            }
        }
    }
}




