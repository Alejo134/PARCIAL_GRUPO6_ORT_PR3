package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Un campo de texto custom para la entrada de un PIN de 'pinLength' dígitos.
 */
@Composable
fun PinInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    pinLength: Int = 6
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= pinLength && it.all { c -> c.isDigit() }) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier,
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                repeat(pinLength) { index ->
                    val char = value.getOrNull(index)

                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(48.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (char != null) {
                                Text(
                                    text = char.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}