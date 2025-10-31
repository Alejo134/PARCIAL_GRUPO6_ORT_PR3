package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppPasswordTextField
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.components.OnboardingScreenLayout
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun NewPasswordScreen() {

    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    OnboardingScreenLayout(
        topScreenWeight = 0.22f,
        topContent = {
            Text(
                text = stringResource(id = R.string.new_password_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        bottomContent = {

            AppPasswordTextField(
                labelResId = R.string.new_password_label,
                value = newPassword,
                onValueChange = { newPassword = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            AppPasswordTextField(
                labelResId = R.string.confirm_new_password_label,
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ButtonLog(
                stringId = R.string.change_password_button,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp),
                onClick = { /* TODO: Lógica de cambiar contraseña */ }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewPasswordScreenPreview() {
    PARCIALPR3ORTTheme {
        NewPasswordScreen()
    }
}