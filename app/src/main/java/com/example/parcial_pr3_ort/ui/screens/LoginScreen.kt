package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.*
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
                onClick = { /* TODO: Lógica de Login */ }
            )

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
                onClick = { /* TODO: Navegar a Sign Up */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            val fingerprintText = buildAnnotatedString {
                append(stringResource(id = R.string.use_fingerprint))
                append(" ")
                withStyle(style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold)
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
                    onClick = { /* TODO: Login Google */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            val bottomText = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account) + " ")
                withStyle(style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold)
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
        LoginScreen()
}