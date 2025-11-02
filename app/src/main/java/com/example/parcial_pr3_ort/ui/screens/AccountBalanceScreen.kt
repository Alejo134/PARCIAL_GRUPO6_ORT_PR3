package com.example.parcial_pr3_ort.ui.screens


import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppNavigationBar
import com.example.parcial_pr3_ort.ui.components.FinancialInfoCard
import com.example.parcial_pr3_ort.ui.components.GoalsCard
import com.example.parcial_pr3_ort.ui.components.MainTopAppBar
import com.example.parcial_pr3_ort.ui.components.CategoryProgressBar
import com.example.parcial_pr3_ort.ui.components.TransactionCard
import kotlin.math.abs

@Composable
fun AccountBalanceScreen(
    totalBalance: Double = 7_783.00,
    totalExpense: Double = -1_187.40,
    income: Double = 4_000.00,
    expense: Double = -1_187.40,
    maxBudget: Double = 20_000.00,
    transactions: List<Tx> = sampleTransactions(),
    onBackClick: () -> Unit = {},
    onBellClick: () -> Unit = {},
    onSeeAllClick: () -> Unit = {},
    onBottomItemSelected: (String) -> Unit = {},
    @DrawableRes totalIcon: Int = R.drawable.totalbalance,
    @DrawableRes totalExpenseIcon: Int = R.drawable.totalexpense,
    @DrawableRes incomeIcon: Int = R.drawable.salary,
    @DrawableRes expenseIcon: Int = R.drawable.transactions
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

    Scaffold(
        topBar = {
            MainTopAppBar(
                currentRoute = "account_balance",
                canNavigateBack = true,
                onNavigateBack = onBackClick,
                onNotificationClick = onBellClick
            )
        },
        bottomBar = {
            AppNavigationBar(
                currentRoute = "account_balance",
                onNavItemClick = onBottomItemSelected
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.primary) // tu verde header
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FinancialInfoCard(
                            title = "Total Balance",
                            value = totalBalance,
                            iconResId = totalIcon,
                            valueColor = Color.White,
                            modifier = Modifier.weight(1f)
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

                    GoalsCard(modifier = Modifier.fillMaxWidth())

                    Spacer(Modifier.height(8.dp))

                    ProgressBar(
                        progress = usage.toFloat(),
                        trackColor = Color.White.copy(alpha = 0.30f),
                        progressColor = Color.White,
                        height = 6.dp,
                        shape = RoundedCornerShape(50)
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        FinancialInfoCard(
                            title = "Income",
                            value = income,
                            iconResId = incomeIcon,
                            valueColor = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        FinancialInfoCard(
                            title = "Expense",
                            value = expense,
                            iconResId = expenseIcon,
                            valueColor = Color(0xFF1E9BFF),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$usagePct% Of Your Expenses, Looks Good.",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 18.dp, vertical = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                                .clickable { onSeeAllClick() }
                                .padding(4.dp)
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }

            items(transactions) { tx ->
                TransactionCard(
                    subtype = tx.subtype,
                    date = tx.date,
                    amount = tx.amount,
                    type = tx.type,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                )
                Divider(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                )
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}


data class Tx(
    val subtype: String,
    val date: String,
    val amount: Double,
    val type: String
)

fun sampleTransactions() = listOf(
    Tx("Salary", "18:27 - April 30", 4000.00, "income"),
    Tx("Groceries", "17:00 - April 24", -100.00, "expense"),
    Tx("Rent", "8:30 - April 15", -674.40, "expense"),
    Tx("Transport", "9:30 - April 08", -4.13, "expense")
)
