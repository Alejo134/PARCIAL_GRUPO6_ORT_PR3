package com.example.parcial_pr3_ort.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun ChangePinScreen(
    onChangePinClick: () -> Unit = {}
) {
    var currentPin by remember { mutableStateOf("") }
    var newPin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var currentPinVisible by remember { mutableStateOf(false) }
    var newPinVisible by remember { mutableStateOf(false) }
    var confirmPinVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Form container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .background(Honeydew)
                .padding(horizontal = 32.dp, vertical = 40.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // PIN fields section
                Column {
                    // Current Pin
                    PinInputField(
                        label = "Current Pin",
                        value = currentPin,
                        onValueChange = { if (it.length <= 4) currentPin = it },
                        isVisible = currentPinVisible,
                        onVisibilityToggle = { currentPinVisible = !currentPinVisible }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // New Pin
                    PinInputField(
                        label = "New Pin",
                        value = newPin,
                        onValueChange = { if (it.length <= 4) newPin = it },
                        isVisible = newPinVisible,
                        onVisibilityToggle = { newPinVisible = !newPinVisible }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Confirm Pin
                    PinInputField(
                        label = "Confirm Pin",
                        value = confirmPin,
                        onValueChange = { if (it.length <= 4) confirmPin = it },
                        isVisible = confirmPinVisible,
                        onVisibilityToggle = { confirmPinVisible = !confirmPinVisible }
                    )
                }

                // Change Pin button
                Button(
                    onClick = onChangePinClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CaribbeanGreen
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Change Pin",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun PinInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Column {
        // Label
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = FenceGreen,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Input field with dots and eye icon
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LightGreen)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // PIN input with dots
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        color = FenceGreen,
                        letterSpacing = 12.sp,
                        textAlign = TextAlign.Start
                    ),
                    visualTransformation = if (isVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = "••••",
                                    fontSize = 24.sp,
                                    color = FenceGreen.copy(alpha = 0.3f),
                                    letterSpacing = 12.sp
                                )
                            } else {
                                innerTextField()
                            }
                        }
                    }
                )

                // Eye icon toggle
                IconButton(
                    onClick = onVisibilityToggle,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (isVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (isVisible) "Hide PIN" else "Show PIN",
                        tint = FenceGreen
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePinScreenPreview() {
    PARCIALPR3ORTTheme {
        Surface {
            ChangePinScreen()
        }
    }
}
