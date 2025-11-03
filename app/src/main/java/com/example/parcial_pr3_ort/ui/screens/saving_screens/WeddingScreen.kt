package com.example.parcial_pr3_ort.ui.screens.saving_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.AppDataBase
import com.example.parcial_pr3_ort.data.TransactionRepository
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.ButtonAddExpenses
import com.example.parcial_pr3_ort.ui.components.ProgressBar
import com.example.parcial_pr3_ort.ui.components.TransactionCategory
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.viewmodel.CategoryDetailViewModel
import com.example.parcial_pr3_ort.viewmodel.CategoryDetailViewModelFactory
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory
import kotlin.collections.component1
import kotlin.collections.component2

// He creado este componente reutilizable para la sección del título con ícono
// Esto hace que el código sea más limpio y evita la repetición.
@Composable
private fun GoalInfo(
    title: String,
    value: String,
    image: Int,
    colorSaved: Color,
) {
    Column {
        // Fila para el título "Goal" y su ícono
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre el ícono y el texto
        ) {
            Icon(
                painter = painterResource(id = image),
                contentDescription = "$title icon",
                tint = FenceGreen, // Un color sutil
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                color = FenceGreen,
                fontWeight = FontWeight.SemiBold
            )
        }
        // Texto con el valor, debajo del título
        Text(
            color = colorSaved,
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 28.dp) // Padding para alinear con el texto de arriba
        )
    }
}


@Composable
fun WeddingScreen(navController: NavController) {



    val context = LocalContext.current
    val viewModel: CategoryDetailViewModel = viewModel(
        factory = CategoryDetailViewModelFactory(
            repository = TransactionRepository(AppDataBase.getDatabase(context).transactionDao()),
            categoryName = "Wedding"
        )
    )

    val groupedTransactions by viewModel.groupedTransactions.collectAsState()




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


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Centra los hijos horizontalmente
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(start = 40.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        GoalInfo(
                            title = "Goal",
                            value = "$1,962.93",
                            image = R.drawable.totalbalance,
                            colorSaved = FenceGreen
                        )
                        GoalInfo(
                            title = "Saved",
                            value = "$653.31",
                            image = R.drawable.totalexpense,
                            colorSaved = CaribbeanGreen,
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.weddinggoal),
                        contentDescription = "Wedding Goal",
                        modifier = Modifier.size(170.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ProgressBar(
                categoryName = "30% of your expenses, looks good.",
                currentValue = 1962.93,
                colorProgressBar = CaribbeanGreen
            )





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
            }
        }
        ButtonAddExpenses(
            onClick = {
                navController.navigate("add_savings_screen")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp), text = "Add Saving"
        )
    }



}






