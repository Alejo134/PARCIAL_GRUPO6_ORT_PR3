@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.parcial_pr3_ort.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.data.model.profile.ProfileBankAccount
import com.example.parcial_pr3_ort.data.model.profile.ProfileCreditCard
import com.example.parcial_pr3_ort.data.model.profile.ProfileTransactions
import com.example.parcial_pr3_ort.data.model.profile.ProfileTxItem
import com.example.parcial_pr3_ort.data.model.profile.UserProfile
import com.example.parcial_pr3_ort.viewmodel.ProfileUiState
import com.example.parcial_pr3_ort.viewmodel.ProfileViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(),
    userId: Int = 12345
) {
    // Carga inicial
    LaunchedEffect(userId) { viewModel.load(userId) }

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile") }) }
    ) { padding ->
        when (state) {
            is ProfileUiState.Loading -> androidx.compose.foundation.layout.Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) { androidx.compose.material3.CircularProgressIndicator() }

            is ProfileUiState.Error -> {
                val msg = (state as ProfileUiState.Error).message
                ErrorState(
                    modifier = Modifier.padding(padding),
                    message = msg,
                    onRetry = { viewModel.load(userId) }
                )
            }

            is ProfileUiState.Success -> {
                val data = (state as ProfileUiState.Success).data
                ProfileContent(
                    data = data,
                    contentPadding = padding
                )
            }
        }
    }
}

/* ===================== Secciones UI ===================== */

@Composable
private fun HeaderSection(name: String, email: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Avatar",
            modifier = Modifier.size(72.dp)
        )
        Spacer(Modifier.padding(8.dp))
        Column {
            Text(name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun BalanceCard(balance: Double) {
    ElevatedCard {
        Column(Modifier.padding(16.dp)) {
            Text("Balance", style = MaterialTheme.typography.labelLarge)
            Text(
                formatArs(balance),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun CreditCardSection(
    cardholder: String,
    numberMasked: String,
    exp: String,
    limit: Double,
    current: Double,
    available: Double
) {
    ElevatedCard {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Credit card", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("$cardholder • $numberMasked • exp $exp", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            KeyValue("Limit", formatArs(limit))
            KeyValue("Current balance", formatArs(current))
            KeyValue("Available", formatArs(available))
        }
    }
}

@Composable
private fun BankAccountSection(
    bankName: String,
    type: String,
    cvu: String,
    alias: String,
    currency: String
) {
    ElevatedCard {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Bank account", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("$bankName • $type • $currency", style = MaterialTheme.typography.bodyMedium)
            KeyValue("CVU", cvu)
            KeyValue("Alias", alias)
        }
    }
}

@Composable private fun SectionTitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun TransactionRow(tx: ProfileTxItem) {
    val sign = if (tx.type.lowercase() == "debit") "-" else "+"
    ListItem(
        headlineContent = { Text(tx.description) },
        supportingContent = { Text(tx.date) },
        trailingContent = { Text("$sign ${formatArs(tx.amount)}", fontWeight = FontWeight.SemiBold) }
    )
    Divider()
}

@Composable
private fun SettingsList(
    onEditProfile: () -> Unit,
    onNotifications: () -> Unit,
    onSecurity: () -> Unit,
    onLanguage: () -> Unit,
    onLogout: () -> Unit
) {
    ElevatedCard {
        Column {
            SettingsItem("Edit profile", onEditProfile)
            SettingsItem("Notifications", onNotifications)
            SettingsItem("Security", onSecurity)
            SettingsItem("Language", onLanguage)
            SettingsItem("Logout", onLogout)
        }
    }
}

@Composable
private fun SettingsItem(text: String, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick() },
        headlineContent = { Text(text) }
    )
    Divider()
}

/* ===================== Utilidades ===================== */

@Composable
private fun ErrorState(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ocurrió un error", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text(message, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRetry) { Text("Reintentar") }
    }
}

private fun formatArs(value: Double): String {
    val nf = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
    return nf.format(value)
}

private fun maskCard(number: String): String {
    val last4 = number.takeLast(4)
    return "**** **** **** $last4"
}

@Composable
private fun KeyValue(key: String, value: String) {
    Row(
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(key, style = MaterialTheme.typography.bodyMedium)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

/* ===================== CONTENT + PREVIEW ===================== */

@Composable
private fun ProfileContent(
    data: UserProfile,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HeaderSection(name = data.name, email = data.email) }
        item { BalanceCard(balance = data.balance) }
        item {
            CreditCardSection(
                cardholder = data.creditCard.cardholderName,
                numberMasked = maskCard(data.creditCard.cardNumber),
                exp = data.creditCard.expirationDate,
                limit = data.creditCard.creditLimit,
                current = data.creditCard.currentBalance,
                available = data.creditCard.availableBalance
            )
        }
        item {
            BankAccountSection(
                bankName = data.bankAccount.bankName,
                type = data.bankAccount.accountType,
                cvu = data.bankAccount.cvu,
                alias = data.bankAccount.alias,
                currency = data.bankAccount.currency
            )
        }
        item { SectionTitle("Credit card transactions") }
        items(data.transactions.creditCardTransactions.take(3)) { tx ->
            TransactionRow(tx)
        }
        item { SectionTitle("Bank account transactions") }
        items(data.transactions.bankAccountTransactions.take(3)) { tx ->
            TransactionRow(tx)
        }
        item {
            SettingsList(
                onEditProfile = { },
                onNotifications = { },
                onSecurity = { },
                onLanguage = { },
                onLogout = { }
            )
        }
    }
}

// Mocks para preview
private fun sampleUserProfile(): UserProfile =
    UserProfile(
        userId = "12345",
        name = "Juan Pérez",
        email = "juan.perez@email.com",
        balance = 1250.50,
        creditCard = ProfileCreditCard(
            cardNumber = "1234 5678 9012 3456",
            cardholderName = "Juan Pérez",
            expirationDate = "12/25",
            cvv = "123",
            creditLimit = 50000.0,
            currentBalance = 12000.0,
            availableBalance = 38000.0
        ),
        bankAccount = ProfileBankAccount(
            bankName = "Waynimóvil",
            accountType = "CVU",
            cvu = "0000003100045691234567",
            alias = "juan.billetera",
            currency = "ARS"
        ),
        transactions = ProfileTransactions(
            creditCardTransactions = listOf(
                ProfileTxItem("001", "2024-10-01", "Compra en supermercado", 2500.75, "ARS", "debit"),
                ProfileTxItem("002", "2024-10-10", "Compra en tienda de ropa", 3500.0, "ARS", "debit"),
                ProfileTxItem("004", "2024-10-20", "Devolución electrónicos", 1500.0, "ARS", "credit")
            ),
            bankAccountTransactions = listOf(
                ProfileTxItem("101", "2024-10-02", "Transferencia recibida", 10000.0, "ARS", "credit"),
                ProfileTxItem("102", "2024-10-05", "Pago de alquiler", 5000.0, "ARS", "debit"),
                ProfileTxItem("104", "2024-10-25", "Transferencia enviada", 3000.0, "ARS", "debit")
            )
        )
    )

@Preview(showBackground = true, name = "Profile - Light")
@Composable
private fun ProfileScreenPreview_Light() {
    MaterialTheme {
        ProfileContent(sampleUserProfile())
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Profile - Dark"
)
@Composable
private fun ProfileScreenPreview_Dark() {
    MaterialTheme {
        ProfileContent(sampleUserProfile())
    }
}
