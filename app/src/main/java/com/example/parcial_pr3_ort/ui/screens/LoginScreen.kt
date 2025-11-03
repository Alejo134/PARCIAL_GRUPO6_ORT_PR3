package com.example.parcial_pr3_ort.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppPasswordTextField
import com.example.parcial_pr3_ort.ui.components.AppTextField
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.components.OnboardingScreenLayout
import com.example.parcial_pr3_ort.ui.components.SocialLoginButton
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import com.example.parcial_pr3_ort.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val isLoading = uiState is LoginUIState.Loading

    LaunchedEffect(uiState) {
        when (val state = uiState) {
            is LoginUIState.Success -> {
                Toast.makeText(
                    context,
                    "Login OK. Token: ${state.response.token}",
                    Toast.LENGTH_LONG
                ).show()
                // TODO: Aquí iría la navegación a la pantalla de Home
                viewModel.resetState()
            }

            is LoginUIState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                viewModel.resetState()
            }

            else -> {}
        }
    }

    OnboardingScreenLayout(
        topScreenWeight = 0.22f,
        topContent = {
            Text(
                text = stringResource(id = R.string.login_welcome),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        bottomContent = {

            AppTextField(
                labelResId = R.string.username_or_email,
                placeholderResId = R.string.username_placeholder,
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppPasswordTextField(
                labelResId = R.string.password,
                value = password,
                onValueChange = { password = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonLog(
                stringId = R.string.log_in,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onPrimary,
                onClick = { viewModel.login(email, password) },
                enabled = !isLoading
            )

            if (isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.forgot_password))
                },
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                ),
                onClick = { /* TODO: Navegar a Forgot Password */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonLog(
                stringId = R.string.sign_up,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                onClick = { /* TODO: Navegar a Sign Up */ },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            val fingerprintText = buildAnnotatedString {
                append(stringResource(id = R.string.use_fingerprint))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.fingerprint_highlight))
                }
                append(" ")
                append(stringResource(id = R.string.to_access))
            }
            ClickableText(
                text = fingerprintText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                onClick = { /* TODO: Lógica de Biométricos */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.or_sign_up_with),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SocialLoginButton(
                    iconResId = R.drawable.ic_facebook,
                    contentDescResId = R.string.facebook_logo_desc,
                    onClick = { /* TODO: Login Facebook */ }
                )
                SocialLoginButton(
                    iconResId = R.drawable.ic_google,
                    contentDescResId = R.string.google_logo_desc,
                    onClick = { }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            val bottomText = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account) + " ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    pushStringAnnotation(tag = "SIGNUP_TAG", annotation = "signup")
                    append(stringResource(id = R.string.sign_up))
                    pop()
                }
            }
            ClickableText(
                text = bottomText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                onClick = { offset ->
                    bottomText.getStringAnnotations("SIGNUP_TAG", offset, offset)
                        .firstOrNull()?.let {
                            // TODO: Navegar a Sign Up
                        }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PARCIALPR3ORTTheme {
        LoginScreen()
    }
}