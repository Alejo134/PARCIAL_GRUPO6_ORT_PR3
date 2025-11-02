package com.example.parcial_pr3_ort.ui.screens

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppNavigationBar
import com.example.parcial_pr3_ort.ui.components.CategoryProgressBar
import com.example.parcial_pr3_ort.ui.components.FinancialInfoCard
import com.example.parcial_pr3_ort.ui.components.TransactionCard
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    totalBalance: Double = 7_783.00,
    totalExpense: Double = -1_187.40,
    income: Double = 4_000.00,
    expense: Double = -1_187.40,
    maxBudget: Double = 20_000.00,
    transactions: List<TxItem> = sampleTxItems(),
    onNavigateBack: () -> Unit = {},
    onBellClick: () -> Unit = {},
    onNavItemClick: (String) -> Unit = {},
    @DrawableRes backIcon: Int = R.drawable.exit,
    @DrawableRes bellIcon: Int = R.drawable.campana,
    @DrawableRes totalIcon: Int = R.drawable.totalbalance,
    @DrawableRes totalExpenseIcon: Int = R.drawable.totalexpense,
    @DrawableRes incomeIcon: Int = R.drawable.salary,
    @DrawableRes expenseIcon: Int = R.drawable.transactions,
    @DrawableRes foodIcon: Int = R.drawable.food,
    @DrawableRes groceryIcon: Int = R.drawable.categorygrocery,
    @DrawableRes clothesIcon: Int = R.drawable.clothescategory
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
            CenterAlignedTopAppBar(
                title = { Text("Transaction", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(painterResource(id = backIcon), contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = onBellClick) {
                        Icon(painterResource(id = bellIcon), contentDescription = "Notifications", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = CaribbeanGreen,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            AppNavigationBar(
                currentRoute = "transactions",
                onNavItemClick = onNavItemClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(CaribbeanGreen)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    FinancialInfoCard(
                        title = "Total Balance",
                        value = totalBalance,
                        iconResId = totalIcon,
                        valueColor = Honeydew,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FinancialInfoCard(
                            title = "Total Balance",
                            value = totalBalance,
                            iconResId = totalIcon,
                            valueColor = Honeydew,
                            modifier = Modifier.weight(1f)
                        )
                        FinancialInfoCard(
                            title = "Total Expense",
                            value = totalExpense,
                            iconResId = totalExpenseIcon,
                            valueColor = OceanBlue,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    CategoryProgressBar(
                        categoryName = "$usagePct% of your expenses, looks good.",
                        currentValue = maxBudget
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 16.dp, vertical = 18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        FinancialInfoCard(
                            title = "Income",
                            value = income,
                            iconResId = incomeIcon,
                            valueColor = FenceGreen,
                            modifier = Modifier.weight(1f)
                        )
                        FinancialInfoCard(
                            title = "Expense",
                            value = expense,
                            iconResId = expenseIcon,
                            valueColor = OceanBlue,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

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
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            val monthsOrder = listOf("April", "March")
            val grouped = transactions.groupBy { it.month }

            monthsOrder.forEach { month ->
                val list = grouped[month].orEmpty()
                if (list.isNotEmpty()) {
                    item {
                        Text(
                            text = month,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 18.dp, vertical = 8.dp),
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    items(list) { tx ->
                        TransactionCard(
                            subtype = tx.subtype,
                            date = tx.date,
                            amount = tx.amount,
                            type = tx.type,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 18.dp, vertical = 8.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 18.dp),
                            thickness = 1.dp,
                            color = CaribbeanGreen.copy(alpha = 0.15f)
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(20.dp)) }
        }
    }
}

data class TxItem(
    val month: String,
    val subtype: String,
    val date: String,
    val amount: Double,
    val type: String
)

fun sampleTxItems() = listOf(
    TxItem("April", "Salary", "18:27 - April 30", 4000.00, "income"),
    TxItem("April", "Groceries", "17:00 - April 24", -100.00, "expense"),
    TxItem("April", "Rent", "8:30 - April 15", -674.40, "expense"),
    TxItem("April", "Transport", "9:30 - April 08", -4.13, "expense"),
    TxItem("March", "Food", "18:30 - March 31", -70.40, "expense")
)


