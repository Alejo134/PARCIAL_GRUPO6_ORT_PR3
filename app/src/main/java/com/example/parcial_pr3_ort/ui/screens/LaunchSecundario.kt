package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.ButtonLog
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen

@Composable
fun PreWelcomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Honeydew
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.segundo_vector),
                contentDescription = stringResource(id = R.string.logo_description),
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(13.dp))

            Text(
                text = stringResource(id = R.string.brand_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = CaribbeanGreen,
                fontSize = 55.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.welcome_tagline),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(48.dp))

            ButtonLog(
                stringId = R.string.log_in,
                backgroundColor = CaribbeanGreen,
                textColor = Cyprus,
                onClick = { /* TODO: Log In */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonLog(
                stringId = R.string.sign_up,
                backgroundColor = LightGreen,
                textColor = Cyprus,
                onClick = { /* TODO: Sign Up */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.clickable { /* TODO: Forgot Pass */ }
            )
        }
    }
}

@Preview
@Composable
fun PreWelcomeScreenPreview() {
    PreWelcomeScreen()
}