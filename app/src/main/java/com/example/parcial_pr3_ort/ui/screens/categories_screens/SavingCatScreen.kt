package com.example.parcial_pr3_ort.ui.screens.categories_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import com.example.parcial_pr3_ort.ui.components.ItemCategories
import com.example.parcial_pr3_ort.ui.components.TopPageFinancial_ProgressBar
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.viewmodel.HomeViewModel
import com.example.parcial_pr3_ort.viewmodel.HomeViewModelFactory

@Composable
fun SavingsCatScreen(navController: NavController) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            UserAccountRepository(RetrofitClient.api)
        )
    )

    val totalBalance by homeViewModel.totalBalance
    val totalExpense by homeViewModel.totalExpense

    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
                    val navigationRoute: String
                )

                val categories = listOf(
                    CategoryInfo(
                        "Travel",
                        R.drawable.travelcategory,
                        R.drawable.foodcategory_selected,
                        AppRoutes.TRAVEL_CATEGORY
                    ),
                    CategoryInfo(
                        "New House",
                        R.drawable.newhousecategory,
                        R.drawable.transportcategory_selected,
                        AppRoutes.NEW_HOUSE_CATEGORY
                    ),
                    CategoryInfo(
                        "Car",
                        R.drawable.carcategory,
                        R.drawable.medicinecategory_selected,
                        AppRoutes.CAR_CATEGORY
                    ),
                    CategoryInfo(
                        "Wedding",
                        R.drawable.weddingcategory,
                        R.drawable.groceriescategory_selected,
                        AppRoutes.WEDDING_CATEGORY
                    )
                )


                val categoryRows = categories.chunked(3)

                Column(
                    verticalArrangement = Arrangement.spacedBy(36.dp)
                ) {
                    categoryRows.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            rowItems.forEach { category ->
                                Box(
                                    modifier = Modifier.weight(1f).padding(start=11.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                ItemCategories(
                                    name = category.name,
                                    icon = painterResource(id = category.iconRes),
                                    selectedIcon = painterResource(id = category.selectedIconRes),
                                    isSelected = false,
                                    onClick = { navController.navigate(category.navigationRoute) }
                                )
                            }
                                }
                        }
                    }
                }


            }
        }
    }
}




