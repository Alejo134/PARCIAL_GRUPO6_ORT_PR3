package com.example.parcial_pr3_ort.ui.screens.launch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.components.OnboardingScreenLayout
import com.example.parcial_pr3_ort.ui.components.PinInputField
import com.example.parcial_pr3_ort.ui.components.SocialLoginButton
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun SecurityPinScreen(navController: NavController) {

    var pinValue by rememberSaveable { mutableStateOf("") }

    OnboardingScreenLayout(
        topScreenWeight = 0.22f,
        topContent = {
            Text(
                text = stringResource(id = R.string.security_pin_title),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        bottomContent = {

            Text(
                text = stringResource(id = R.string.enter_security_pin),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            PinInputField(
                value = pinValue,
                onValueChange = { pinValue = it },
                pinLength = 6
            )

            Spacer(modifier = Modifier.height(32.dp))

            ButtonLog(
                stringId = R.string.accept,
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = { navController.navigate(AppRoutes.NEW_PASSWORD) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonLog(
                stringId = R.string.send_again,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                textColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                onClick = { /* TODO: Lógica de Reenviar */ }
            )

            Spacer(modifier = Modifier.weight(1f))

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

            Spacer(modifier = Modifier.height(16.dp))
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SecurityPinScreenPreview() {
    PARCIALPR3ORTTheme {
        SecurityPinScreen(navController = rememberNavController())
    }
}