package com.example.parcial_pr3_ort.ui.screens.profile_screens_2

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.data.AppDataBase
import com.example.parcial_pr3_ort.data.TransactionRepository
import com.example.parcial_pr3_ort.ui.components.ButtonAddExpenses
import com.example.parcial_pr3_ort.ui.screens.CategoryDropdown
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.viewmodels.AddExpenseViewModel
import com.example.parcial_pr3_ort.viewmodels.AddExpenseViewModelFactory
import java.util.Date
import java.util.Locale

val categoriesSaving = listOf(
    "Travel",
    "New House",
    "Car",
    "Wedding"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomStyledTextField(
    label: String,
    value: String,
    heightTextField: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(modifier = modifier) {
        // 1. Label encima del campo
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Cyprus,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .width(350.dp)
                .height(heightTextField.dp),
            shape = RoundedCornerShape(18), // Forma de pastilla
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LightGreen,
                focusedContainerColor = LightGreen,
                focusedIndicatorColor = Color.Transparent, // Sin línea inferior al hacer foco
                unfocusedIndicatorColor = Color.Transparent, // Sin línea inferior
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 16.sp, // Tamaño de fuente deseado
                fontWeight = FontWeight.Normal // Peso de la fuente deseado
            ),
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSavingsScreen(navController: NavController) {

    // Estados para guardar los valores de los campos
    var selectedCategory by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var expenseTitle by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val context = LocalContext.current
    val database = AppDataBase.getDatabase(context)
    val repository = TransactionRepository(database.transactionDao())
    val viewModel: AddExpenseViewModel = viewModel(factory = AddExpenseViewModelFactory(repository))

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val currentDate = dateFormatter.format(Date())

    var selectedDate by remember { mutableStateOf(currentDate) }
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
            Text(
                "Add Expense",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 1. Campo de Fecha
            CustomStyledTextField(
                label = "Date",
                value = selectedDate,
                onValueChange = {},
                readOnly = true,
                heightTextField = 50
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 2. Dropdown de Categoría
            CategoryDropdown(
                categories = categoriesSaving,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Campo de Monto (Amount)
            CustomStyledTextField(
                label = "Amount",
                value = amount,
                onValueChange = { amount = it },
                heightTextField = 50,
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 4. Campo de Título del Gasto
            CustomStyledTextField(
                label = "Expense Title",
                value = expenseTitle,
                heightTextField = 50,
                onValueChange = { expenseTitle = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 5. Campo de Mensaje
            CustomStyledTextField(
                label = "Enter Message",
                value = message,
                onValueChange = { message = it },
                heightTextField = 140,
            )

            Spacer(modifier = Modifier.height(70.dp))

            // Botón para guardar
            ButtonAddExpenses(onClick = {

                val dateLong = dateFormatter.parse(selectedDate)?.time ?: System.currentTimeMillis()

                val amountDouble = amount.toDoubleOrNull() ?: 0.0
                viewModel.saveTransaction(
                    title = expenseTitle,
                    amount = amountDouble,
                    date = dateLong,
                    category = selectedCategory,
                    message = message
                )
                navController.popBackStack()
            }, modifier = Modifier, "Save")

        }
    }
}


