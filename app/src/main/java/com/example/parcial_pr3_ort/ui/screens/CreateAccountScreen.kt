package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.AppPasswordTextField
import com.example.parcial_pr3_ort.ui.components.AppTextField
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.components.OnboardingScreenLayout
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun CreateAccountScreen() {

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var mobile by rememberSaveable { mutableStateOf("") }
    var dob by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    OnboardingScreenLayout(
        topScreenWeight = 0.22f,
        topContent = {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        bottomContent = {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTextField(
                    labelResId = R.string.full_name,
                    placeholderResId = R.string.full_name_placeholder,
                    value = fullName,
                    onValueChange = { fullName = it },
                    keyboardType = KeyboardType.Text
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    labelResId = R.string.email,
                    placeholderResId = R.string.username_placeholder,
                    value = email,
                    onValueChange = { email = it },
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    labelResId = R.string.mobile_number,
                    placeholderResId = R.string.mobile_number_placeholder,
                    value = mobile,
                    onValueChange = { mobile = it },
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppTextField(
                    labelResId = R.string.date_of_birth,
                    placeholderResId = R.string.date_of_birth_placeholder,
                    value = dob,
                    onValueChange = { dob = it },
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppPasswordTextField(
                    labelResId = R.string.password,
                    value = password,
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppPasswordTextField(
                    labelResId = R.string.confirm_password,
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                val termsText = buildAnnotatedString {
                    val linkStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    append(stringResource(id = R.string.terms_prefix))

                    append(" ")

                    pushStringAnnotation(tag = "TERMS_TAG", annotation = "terms")
                    withStyle(style = linkStyle) { append(stringResource(id = R.string.terms_of_use)) }
                    pop()
                    append(" ")
                    append(stringResource(id = R.string.terms_suffix))
                    append(" ")
                    pushStringAnnotation(tag = "PRIVACY_TAG", annotation = "privacy")
                    withStyle(style = linkStyle) { append(stringResource(id = R.string.privacy_policy)) }
                    pop()
                }

                ClickableText(
                    text = termsText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(horizontal = 32.dp),
                    onClick = { offset ->
                        termsText.getStringAnnotations("TERMS_TAG", offset, offset)
                            .firstOrNull()?.let {
                                // TODO: Abrir link a Términos de Uso
                            }
                        termsText.getStringAnnotations("PRIVACY_TAG", offset, offset)
                            .firstOrNull()?.let {
                                // TODO: Abrir link a Política de Privacidad
                            }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                ButtonLog(
                    stringId = R.string.sign_up,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /* TODO: Lógica de Crear Cuenta */ }
                )

                Spacer(modifier = Modifier.height(24.dp))

                val bottomText = buildAnnotatedString {
                    append(stringResource(id = R.string.already_have_account) + " ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        pushStringAnnotation(tag = "LOGIN_TAG", annotation = "login")
                        append(stringResource(id = R.string.log_in))
                        pop()
                    }
                }
                ClickableText(
                    text = bottomText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    onClick = { offset ->
                        bottomText.getStringAnnotations("LOGIN_TAG", offset, offset)
                            .firstOrNull()?.let {
                                // TODO: Navegar de vuelta a LoginScreen
                            }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacio al final
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    PARCIALPR3ORTTheme {
        CreateAccountScreen()
    }
}