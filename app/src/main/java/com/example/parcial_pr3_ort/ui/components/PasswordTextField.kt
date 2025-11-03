package com.example.parcial_pr3_ort.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R

/**
 * Un campo de texto para contraseñas, con toggle de visibilidad.
 */
@Composable
fun AppPasswordTextField(
    @StringRes labelResId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(id = labelResId),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            enabled = enabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val (icon, descriptionResId) = if (passwordVisible) {
                    (painterResource(id = R.drawable.ic_eye_open) to R.string.password_visibility_hide_desc)
                } else {
                    (painterResource(id = R.drawable.ic_eye_closed) to R.string.password_visibility_show_desc)
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = icon,
                        contentDescription = stringResource(id = descriptionResId),
                        tint = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
                    )
                }
            }
        )
    }
}