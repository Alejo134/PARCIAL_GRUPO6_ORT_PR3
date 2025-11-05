package com.example.parcial_pr3_ort.ui.screens.profile_screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineSupportScreen(navController: NavController) {


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
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Active Chats",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth().padding(start = 42.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            NotificationCard(
                iconRes = R.drawable.bot_support,
                title = "Help Center",
                message = "Your account is ready to use...",
                date = "Feb 08 - 2024"
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ended Chats",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth().padding(start = 42.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            NotificationCard(
                iconRes = R.drawable.bot_support,
                title = "Help Center",
                message = "Your account is ready to use...",
                date = "Feb 08 - 2024"
            )
            Spacer(modifier = Modifier.height(16.dp))

            NotificationCard(
                iconRes = R.drawable.bot_support,
                title = "Help Center",
                message = "Your account is ready to use...",
                date = "Feb 08 - 2024"
            )
            Spacer(modifier = Modifier.height(16.dp))

            NotificationCard(
                iconRes = R.drawable.bot_support,
                title = "Help Center",
                message = "Your account is ready to use...",
                date = "Feb 08 - 2024"
            )
            Spacer(modifier = Modifier.height(16.dp))

            NotificationCard(
                iconRes = R.drawable.bot_support,
                title = "Help Center",
                message = "Your account is ready to use...",
                date = "Feb 08 - 2024"
            )

        }
    }
}









@Composable
fun NotificationCard(
    @DrawableRes iconRes: Int,
    title: String,
    message: String,
    date: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE4F6E9), // verde suave
    iconBackgroundColor: Color = Color(0xFF0FD092), // verde brillante del ícono
    textColor: Color = Color(0xFF173B2F) // verde oscuro para texto
) {
    Card(
        modifier = modifier
            .width(357.dp)
            .height(75.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .background(color = iconBackgroundColor, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }

                // Textos
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = message,
                        color = textColor.copy(alpha = 0.8f),
                        fontSize = 13.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .width(88.dp)
                    .height(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date,
                    color = textColor.copy(alpha = 0.9f),
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}