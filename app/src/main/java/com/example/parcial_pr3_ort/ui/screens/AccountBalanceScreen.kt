package com.example.parcial_pr3_ort.ui.screens


import android.app.Activity
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.components.StatButton
import com.example.components.StatButtonDefaults
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.FinancialInfoCard
import com.example.parcial_pr3_ort.ui.components.ProgressBar
import com.example.parcial_pr3_ort.ui.components.TransactionCard
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory
import kotlin.math.abs

@Composable
fun AccountBalanceScreen(
    totalBalance: Double = 7_783.00,
    totalExpense: Double = -1_187.40,
    income: Double = 4_000.00,
    expense: Double = -1_187.40,
    maxBudget: Double = 20_000.00,
    @DrawableRes totalIcon: Int = R.drawable.totalbalance,
    @DrawableRes totalExpenseIcon: Int = R.drawable.totalexpense,
    @DrawableRes incomeIcon: Int = R.drawable.salary,
    @DrawableRes expenseIcon: Int = R.drawable.transactions,
) {
    val usage = (abs(totalExpense) / maxBudget).coerceIn(0.0, 1.0)
    val usagePct = (usage * 100).toInt()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

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
    var selected by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FinancialInfoCard(
                title = "Total Balance",
                value = totalBalance,
                iconResId = totalIcon,
                valueColor = Color.White,
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
                value = totalExpense,
                iconResId = totalExpenseIcon,
                valueColor = Color(0xFF1E9BFF),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(14.dp))



        ProgressBar(
            categoryName = "",
            currentValue = 20000.0,
            colorProgressBar = Honeydew,
            colorIcon = Color.Transparent,
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            StatButton(
                iconRes = R.drawable.check,
                title = "Income",
                value = "$4,120.00",
                selected = selected,
                onClick = {  },
                colors = StatButtonDefaults.colors(
                    container = MaterialTheme.colorScheme.surface,
                    content = MaterialTheme.colorScheme.primary,        // color normal
                    containerSelected = OceanBlue,
                    contentSelected = Honeydew
                )
            )
            Spacer(Modifier.width(16.dp))

            StatButton(
                iconRes = R.drawable.check,
                title = "Expense",
                value = "$1,187.40",
                selected = selected,
                onClick = { },
                colors = StatButtonDefaults.colors(
                    container = MaterialTheme.colorScheme.surface,
                    content = OceanBlue,
                    containerSelected = OceanBlue,
                    contentSelected = Honeydew
                )
            )
        }


        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "check icon",
                tint = FenceGreen,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "30% of your expenses, looks good.",
                color = FenceGreen,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Spacer(Modifier.height(24.dp))


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
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 25.dp, end = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transactions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "See all",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {  }
                            .padding(4.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))


                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    errorMessage != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = errorMessage!!)
                        }
                    }

                    else -> {
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

