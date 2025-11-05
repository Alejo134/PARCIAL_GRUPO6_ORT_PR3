package com.example.parcial_pr3_ort.ui.screens.profile_screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpFaqScreen(navController: NavController) {

    val options = listOf("FAQ", "Contact Us")
    var selectedOption by remember { mutableStateOf(options.first()) }



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
                text = "How Can We Help You?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 25.dp, start = 16.dp, end = 16.dp)
                    .width(350.dp)
            )

            ContentSelector(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { newOption -> selectedOption = newOption }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(40.dp)
                    .clip(
                        RoundedCornerShape(50.dp)
                    )
                    .background(LightGreen)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "General", color = Color.Black)

                    Text(text = "Account", color = Color.Black)

                    Text(text = "Services", color = Color.Black)
                }
            }

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                readOnly = false,
                placeholder = {
                    Text(text = "Search", color = Color.Black)
                },
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = LightGreen,
                    unfocusedContainerColor = LightGreen,
                    disabledContainerColor = LightGreen,

                    unfocusedPlaceholderColor = Honeydew.copy(alpha = 0.7f),
                    focusedPlaceholderColor = Honeydew.copy(alpha = 0.7f),
                    unfocusedLeadingIconColor = Honeydew,
                    focusedLeadingIconColor = Honeydew,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            when (selectedOption) {
                "FAQ" -> FaqContent()
                "Contact Us" -> ContactUsContent(navController = navController)
            }


        }


    }
}




@Composable
fun ContentSelector(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(350.dp)
            .height(61.dp)
            .clip(RoundedCornerShape(30))
            .background(LightGreen)
            .padding(8.dp), // Espaciado interno
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            val isSelected = selectedOption == option

            Box(
                modifier = Modifier
                    .weight(1f) // Cada opción ocupa el mismo espacio
                    .fillMaxSize()
                    .clip(RoundedCornerShape(30))
                    .background(if (isSelected) CaribbeanGreen else Color.Transparent) // Color si está seleccionado
                    .clickable { onOptionSelected(option) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    color = if (isSelected) Honeydew else Cyprus.copy(alpha = 0.8f),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun FaqContent() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FAQContent("How to use FinWise?")
        FAQContent("How much does it cost to use FinWise?")
        FAQContent("How to contact support?")
        FAQContent("How can I reset my password if I forget it?")
        FAQContent("Are there any privacy or data security measures in place?")
        FAQContent("Can I customize settings within the application?")
        FAQContent("How can I delete my account?")
        FAQContent("How do I access my expense history?")
        FAQContent("Can I use the app offline?")

    }
}

@Composable
fun FAQContent(
    text: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.W300,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Ir a",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}
@Composable
fun ContactUsContent(navController: NavController) {
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingsButton(
            text = "Customer Service",
            icon = painterResource(id = R.drawable.bot_support),
            onClick = { navController.navigate(AppRoutes.ONLINE_SUPPORT) }
        )
        SettingsButton(
            text = "Website",
            icon = painterResource(id = R.drawable.bot_website),
            onClick = {}
        )
        SettingsButton(
            text = "Facebook",
            icon = painterResource(id = R.drawable.bot_facebook),
            onClick = {}
        )
        SettingsButton(
            text = "Whatssapp",
            icon = painterResource(id = R.drawable.bot_whatssapp),
            onClick = {}
        )
        SettingsButton(
            text = "Instagram",
            icon = painterResource(id = R.drawable.bot_instagram),
            onClick = {}
        )
    }
}