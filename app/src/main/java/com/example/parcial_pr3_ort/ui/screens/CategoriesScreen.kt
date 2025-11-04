package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.ItemCategories
import com.example.parcial_pr3_ort.ui.components.TopPageFinancial_ProgressBar
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory

@Composable
fun CategoriesScreen(navController: NavController) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            UserAccountRepository(RetrofitClient.api)
        )
    )

    val totalBalance by homeViewModel.totalBalance
    val totalExpense by homeViewModel.totalExpense
    var newCategoryName by remember { mutableStateOf("") }

    var showMoreDialog by remember { mutableStateOf(false) }

    if (showMoreDialog) {
        AlertDialog(
            onDismissRequest = {
                showMoreDialog = false
                newCategoryName = "" // Limpia el texto si se cierra el diálogo
            },
            // El título ya está centrado por defecto en el AlertDialog.
            title = {
                Text(
                    text = "New Category",
                    modifier = Modifier.fillMaxWidth(), // Asegura que el texto pueda centrarse en todo el ancho
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // El TextField para el input
                    TextField(
                        value = newCategoryName,
                        onValueChange = { newCategoryName = it },
                        label = { Text("Write...") },
                        singleLine = true,
                        modifier = Modifier
                            .width(350.dp)
                            .height(50.dp),
                            shape = RoundedCornerShape(50), // Forma de pastilla
                            colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = LightGreen,
                            focusedContainerColor = LightGreen,
                            focusedIndicatorColor = Color.Transparent, // Sin línea inferior al hacer foco
                            unfocusedIndicatorColor = Color.Transparent, // Sin línea inferior
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        // Lógica para guardar...
                        showMoreDialog = false
                        newCategoryName = ""
                    }) {
                        Text("Save")
                    }
                }
            },
            // 5. Botón de descarte/cancelar (izquierda o secundario).
            dismissButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        showMoreDialog = false
                        newCategoryName = ""
                    }) {
                        Text("Cancel")
                    }
                }
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxWidth(), // Usa fillMaxWidth para que se ajuste al ancho del contenedor
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


                data class CategoryInfo(
                    val name: String,
                    val iconRes: Int,
                    val selectedIconRes: Int,
                    val navigationRoute: String,
                )

                val categories = listOf(
                    CategoryInfo(
                        "Food",
                        R.drawable.foodcategory,
                        R.drawable.foodcategory_selected,
                        AppRoutes.FOOD_CATEGORY
                    ),
                    CategoryInfo(
                        "Transport",
                        R.drawable.transportcategory,
                        R.drawable.transportcategory_selected,
                        AppRoutes.TRANSPORT_CATEGORY
                    ),
                    CategoryInfo(
                        "Medicine",
                        R.drawable.medicinecategory,
                        R.drawable.medicinecategory_selected,
                        AppRoutes.MEDICINE_CATEGORY
                    ),
                    CategoryInfo(
                        "Groceries",
                        R.drawable.groceriescategory,
                        R.drawable.groceriescategory_selected,
                        AppRoutes.GROCERIES_CATEGORY
                    ),
                    CategoryInfo(
                        "Rent",
                        R.drawable.rentcategory,
                        R.drawable.rentcategory_selected,
                        AppRoutes.RENT_CATEGORY
                    ),
                    CategoryInfo(
                        "Gifts",
                        R.drawable.giftscategory,
                        R.drawable.giftscategory_selected,
                        AppRoutes.GIFTS_CATEGORY
                    ),
                    CategoryInfo(
                        "Savings",
                        R.drawable.savingscategory,
                        R.drawable.savingscategory_selected,
                        AppRoutes.SAVINGS_CATEGORY
                    ),
                    CategoryInfo(
                        "Entertainment",
                        R.drawable.entertainmentcategory,
                        R.drawable.entertainmentcategory_selected,
                        AppRoutes.ENTERTAINMENT_CATEGORY
                    ),
                    CategoryInfo(
                        "More",
                        R.drawable.morecategory,
                        R.drawable.morecategory_selected,
                        AppRoutes.HOME
                    ),

                )


                val categoryRows = categories.chunked(3)

                Column(
                    verticalArrangement = Arrangement.spacedBy(36.dp)
                ) {
                    categoryRows.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            rowItems.forEach { category ->
                                ItemCategories(
                                    name = category.name,
                                    icon = painterResource(id = category.iconRes),
                                    selectedIcon = painterResource(id = category.selectedIconRes),
                                    isSelected = false,
                                    onClick = {
                                        if (category.name == "More") {
                                            // Si el ítem es "More", muestra el diálogo
                                            showMoreDialog = true
                                        } else {
                                            // Para cualquier otro ítem, navega como antes
                                            navController.navigate(category.navigationRoute)
                                        }
                            }
                                )
                        }
                    }
                }


            }
        }
    }
}
}




