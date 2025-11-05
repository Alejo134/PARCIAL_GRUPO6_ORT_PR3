package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.repository.ProfileRepository
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import com.example.parcial_pr3_ort.ui.theme.VividBlue
import com.example.parcial_pr3_ort.viewmodel.ProfileViewModel
import com.example.parcial_pr3_ort.viewmodel.ProfileViewModelFactory

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onSettingClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            ProfileRepository(RetrofitClient.api)
        )
    )
) {
    val userName = viewModel.userName.value
    val userId = viewModel.userId.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CaribbeanGreen),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
        return
    }

    if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CaribbeanGreen),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = errorMessage,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Profile photo with camera icon overlay
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            // Profile photo circle
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Photo",
                    modifier = Modifier.size(60.dp),
                    tint = Color.White
                )
            }

            // Camera icon overlay
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .offset(x = 35.dp, y = 35.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { /* Handle camera click */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Edit Photo",
                    modifier = Modifier.size(18.dp),
                    tint = FenceGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = FenceGreen
        )

        Text(
            text = "ID: $userId",
            fontSize = 14.sp,
            color = FenceGreen,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Menu container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .background(Honeydew)
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Edit Profile
                ProfileMenuItem(
                    icon = R.drawable.profile,
                    text = "Edit Profile",
                    backgroundColor = VividBlue,
                    onClick = onEditProfileClick
                )

                // Security
                ProfileMenuItem(
                    icon = R.drawable.escudo_tilde,
                    text = "Security",
                    backgroundColor = VividBlue,
                    onClick = onSecurityClick
                )

                // Setting
                ProfileMenuItem(
                    icon = R.drawable.settings_icon,
                    text = "Setting",
                    backgroundColor = VividBlue,
                    onClick = onSettingClick
                )

                // Help
                ProfileMenuItem(
                    icon = R.drawable.soporte,
                    text = "Help",
                    backgroundColor = VividBlue,
                    onClick = onHelpClick
                )

                // Logout
                ProfileMenuItem(
                    icon = R.drawable.exit,
                    text = "Logout",
                    backgroundColor = VividBlue,
                    onClick = onLogoutClick
                )
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon circle
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = text,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Menu text
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = FenceGreen
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PARCIALPR3ORTTheme {
        Surface {
            ProfileScreen()
        }
    }
}
