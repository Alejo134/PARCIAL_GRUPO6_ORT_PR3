@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.parcial_pr3_ort.ui.screens.profile_screens_2

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(rootNavController: NavController) {


    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EndSessionDialog(
            onDismissRequest = {
                showDialog = false
            },
            onConfirm = {
                showDialog = false
                rootNavController.navigate(AppRoutes.LAUNCH) {
                    popUpTo(rootNavController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        )
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.Center
            ) {
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

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .offset(x = 35.dp, y = 35.dp)
                        .clip(CircleShape)
                        .background(CaribbeanGreen)
                        .clickable { /* Handle camera click */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Edit Photo",
                        modifier = Modifier.size(18.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "John Smith",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = FenceGreen
            )

            Text(
                text = "ID: 25030024",
                fontSize = 14.sp,
                color = FenceGreen,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 60.dp,
                    topEnd = 60.dp
                )
            )
            .background(Honeydew)
            .zIndex(0f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally) {



            Spacer(modifier = Modifier.height(24.dp))


            IconTextButton(
                iconRes = R.drawable.icon_profile,
                text = "Edit Profile",
                onClick = { rootNavController.navigate(AppRoutes.EDIT_PROFILE) }
            )

            IconTextButton(
                iconRes = R.drawable.icon_security,
                text = "Security",
                onClick = { rootNavController.navigate(AppRoutes.SECURITY) }
            )

            IconTextButton(
                iconRes = R.drawable.icon_setting,
                text = "Setting",
                onClick = { rootNavController.navigate(AppRoutes.SETTINGS_SCREEN) }
            )

            IconTextButton(
                iconRes = R.drawable.icon_help,
                text = "Help",
                onClick = { rootNavController.navigate(AppRoutes.HELP_FAQ) }
            )

            IconTextButton(
                iconRes = R.drawable.icon_logout,
                text = "Logout",
                onClick = { showDialog = true }
            )

        }

    }
}
        }
    }




@Composable
fun IconTextButton(
    @DrawableRes iconRes: Int,
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Honeydew)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun EndSessionDialog(
    onDismissRequest: () -> Unit, // Acción para cuando el usuario quiere cerrar el diálogo
    onConfirm: () -> Unit        // Acción para cuando el usuario confirma el borrado
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = Honeydew, // Un color de fondo suave
        shape = RoundedCornerShape(28.dp),
        title = {
            Text(
                text = "End Session",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Are you sure you want to log out?",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Espacio para que los botones no se peguen al borde
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonLog(
                    stringId = R.string.yes_end,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(50.dp),
                    onClick = onConfirm
                )

                Spacer(modifier = Modifier.height(8.dp))

                ButtonLog(
                    stringId = R.string.cancel,
                    backgroundColor = LightGreen,
                    textColor = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(50.dp),
                    onClick = onDismissRequest
                )
            }
        }
    )
}
