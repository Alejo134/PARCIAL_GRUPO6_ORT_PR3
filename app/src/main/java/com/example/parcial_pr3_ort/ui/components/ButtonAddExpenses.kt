package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Cyprus

@Composable
fun ButtonAddExpenses(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(169.dp)
            .height(36.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = CaribbeanGreen,
            contentColor = Cyprus
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonAddExpensesPreview() {
    ButtonAddExpenses(onClick = {}, modifier = Modifier, "Add Expense")
}
