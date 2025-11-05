package com.example.parcial_pr3_ort.ui.screens.profile_screens


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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppPasswordTextField
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountScreen(rootNavController: NavController) {

    var currentPassword by rememberSaveable { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ConfirmDeleteDialog(
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
                text = "Are you sure you want to delete your account?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(280.dp)

            )

            DeleteWarningCard()


            Text(
                text = "Please enter your password to confirm deletion of your account.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .width(350.dp)
            )



            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(
                labelResId = R.string.none,
                value = currentPassword,
                onValueChange = { currentPassword = it }
            )



            Spacer(modifier = Modifier.height(44.dp))

            ButtonLog(
                stringId = R.string.yes_delete_account,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .height(50.dp),
                onClick = { showDialog = true }
            )

            Spacer(modifier = Modifier.height(5.dp))

            ButtonLog(
                stringId = R.string.cancel,
                backgroundColor = LightGreen,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp)
                    .height(50.dp),
                onClick = { rootNavController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeleteAccountScreenPreview() {
    PARCIALPR3ORTTheme {
        DeleteAccountScreen(rememberNavController())
    }
}



@Composable
fun DeleteWarningCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFDFF5E1), // verde claro similar al ejemplo
    textColor: Color = Color(0xFF344E41) // tono oscuro suave
) {
    Surface(
        modifier = modifier
            .width(320.dp)
            .wrapContentHeight()
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "This action will permanently delete all of your data, and you will not be able to recover it. Please keep the following in mind before proceeding:",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                BulletText(
                    "All your expenses, income and associated transactions will be eliminated.",
                    textColor
                )
                BulletText(
                    "You will not be able to access your account or any related information.",
                    textColor
                )
                BulletText(
                    "This action cannot be undone.",
                    textColor,
                )
            }
        }
    }
}

@Composable
private fun BulletText(
    text: String,
    color: Color,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "•",
            color = color,
            fontSize = 16.sp
        )
        Text(
            text = text,
            color = color,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun ConfirmDeleteDialog(
    onDismissRequest: () -> Unit, // Acción para cuando el usuario quiere cerrar el diálogo
    onConfirm: () -> Unit        // Acción para cuando el usuario confirma el borrado
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = Honeydew, // Un color de fondo suave
        shape = RoundedCornerShape(28.dp),
        title = {
            Text(
                text = "Delete Account",
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
                Text(
                    text = "By deleting your account, you agree that you understand the consequences of this action and that you agree to permanently delete your account and all associated data.",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
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
                    stringId = R.string.yes_delete,
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