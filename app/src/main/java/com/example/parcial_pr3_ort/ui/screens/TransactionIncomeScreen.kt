package com.example.parcial_pr3_ort.ui.screens



import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
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
import com.example.parcial_pr3_ort.ui.components.FinancialInfoCard
import com.example.parcial_pr3_ort.ui.components.TransactionCard
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.OceanBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionIncomeScreen(
    totalBalance: Double = 7_783.00,
    income: Double = 4_120.00,
    expense: Double = -1_187.40,
    transactions: List<TxItemIncomeV2> = sampleIncomeItems(),
    onNavigateBack: () -> Unit = {},
    onBellClick: () -> Unit = {},
    onNavItemClick: (String) -> Unit = {},
    @DrawableRes backIcon: Int = R.drawable.exit,
    @DrawableRes bellIcon: Int = R.drawable.campana,
    @DrawableRes totalIcon: Int = R.drawable.totalbalance,
    @DrawableRes incomeIcon: Int = R.drawable.salary,
    @DrawableRes expenseIcon: Int = R.drawable.totalexpense
) {
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
                currentRoute = "transactions_income",
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
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        MiniMetricCard(
                            title = "Income",
                            value = income,
                            iconResId = incomeIcon,
                            valueColor = FenceGreen,
                            iconTint = FenceGreen,
                            active = true,
                            modifier = Modifier.weight(1f)
                        )
                        MiniMetricCard(
                            title = "Expense",
                            value = expense,
                            iconResId = expenseIcon,
                            valueColor = OceanBlue,
                            iconTint = OceanBlue,
                            active = false,
                            modifier = Modifier.weight(1f)
                        )
                    }
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
                    SectionTitle("April")
                }
            }

            items(transactions.filter { it.month == "April" }) { tx ->
                TransactionCard(
                    subtype = tx.subtype,
                    date = tx.date,
                    amount = tx.amount,
                    type = "income",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                )
                VerticalDivider(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    thickness = 1.dp,
                    color = CaribbeanGreen.copy(alpha = 0.15f)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    SectionTitle("March")
                }
            }

            items(transactions.filter { it.month == "March" }) { tx ->
                TransactionCard(
                    subtype = tx.subtype,
                    date = tx.date,
                    amount = tx.amount,
                    type = "income",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                )
                VerticalDivider(
                    thickness = 1.dp,
                    color = CaribbeanGreen.copy(alpha = 0.15f),
                    modifier = Modifier.padding(horizontal = 18.dp)
                )
            }

            item { Spacer(Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun MiniMetricCard(
    title: String,
    value: Double,
    @DrawableRes iconResId: Int,
    valueColor: Color,
    iconTint: Color,
    active: Boolean,
    modifier: Modifier = Modifier
) {
    val bg = Color.White
    val borderColor = if (active) CaribbeanGreen.copy(alpha = 0.5f) else Color.Transparent
    val chipBg = if (active) Honeydew else Honeydew.copy(alpha = 0.7f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .then(
                if (borderColor != Color.Transparent)
                    Modifier.border(1.dp, borderColor, RoundedCornerShape(16.dp))
                else Modifier
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(chipBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = title,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = FenceGreen, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Text("$ ${"%,.2f".format(value)}", color = valueColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        fontWeight = FontWeight.ExtraBold
    )
}

data class TxItemIncomeV2(
    val month: String,
    val subtype: String,
    val date: String,
    val amount: Double
)

fun sampleIncomeItems(): List<TxItemIncomeV2> = listOf(
    TxItemIncomeV2("April", "Salary", "18:27 - April 30", 4000.00),
    TxItemIncomeV2("April", "Others", "17:00 - April 24", 120.00),
    TxItemIncomeV2("March", "Salary", "18:30 - March 31", 4000.00),
    TxItemIncomeV2("February", "Others", "18:30 - March 01", 340.00)
)