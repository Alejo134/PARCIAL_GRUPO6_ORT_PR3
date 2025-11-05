package com.example.parcial_pr3_ort.ui.screens.profile_screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingsScreen(navController: NavController) {

    var generalNotificationChecked by remember { mutableStateOf(true) }
    var soundChecked by remember { mutableStateOf(true) }
    var soundCallChecked by remember { mutableStateOf(false) }
    var vibrateChecked by remember { mutableStateOf(true) }
    var transactionUpdateChecked by remember { mutableStateOf(true) }
    var expenseReminderChecked by remember { mutableStateOf(false) }
    var budgetNotificationsChecked by remember { mutableStateOf(true) }
    var lowBalanceAlertsChecked by remember { mutableStateOf(true) }



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
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            SwitchRow(
                text = "General Notification",
                checked = generalNotificationChecked,
                onCheckedChange = { generalNotificationChecked = it }
            )
            SwitchRow(
                text = "Sound",
                checked = soundChecked,
                onCheckedChange = { soundChecked = it }
            )
            SwitchRow(
                text = "Sound Call",
                checked = soundCallChecked,
                onCheckedChange = { soundCallChecked = it }
            )
            SwitchRow(
                text = "Vibrate",
                checked = vibrateChecked,
                onCheckedChange = { vibrateChecked = it }
            )
            SwitchRow(
                text = "Transaction Update",
                checked = transactionUpdateChecked,
                onCheckedChange = { transactionUpdateChecked = it }
            )
            SwitchRow(
                text = "Expense Reminder",
                checked = expenseReminderChecked,
                onCheckedChange = { expenseReminderChecked = it }
            )
            SwitchRow(
                text = "Budget Notifications",
                checked = budgetNotificationsChecked,
                onCheckedChange = { budgetNotificationsChecked = it }
            )
            SwitchRow(
                text = "Low Balance Alerts",
                checked = lowBalanceAlertsChecked,
                onCheckedChange = { lowBalanceAlertsChecked = it }
            )
        }
    }
}




@Composable
fun SwitchRow(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 15.dp, )
            .padding(start = 40.dp, end = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f) // Ocupa todo el espacio, empujando el switch al final
        )

        // 2. Botón de Switch
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = CaribbeanGreen, // Tu color para el fondo cuando está activo
            ),
            modifier = Modifier.height(30.dp).width(15.dp)
        )
    }
}
