package com.example.parcial_pr3_ort.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SocialLoginButton(
    @DrawableRes iconResId: Int,
    @StringRes contentDescResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        color = MaterialTheme.colorScheme.background,
        modifier = modifier.size(52.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = stringResource(id = contentDescResId),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}