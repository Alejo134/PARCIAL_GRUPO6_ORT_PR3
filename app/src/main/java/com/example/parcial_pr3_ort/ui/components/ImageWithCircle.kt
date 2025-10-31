package com.example.parcial_pr3_ort.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.ui.theme.LightGreen

/**
 * Muestra una imagen centrada sobre un fondo circular de color secundario.
 */
@Composable
fun ImageWithCircleBackground(
    @DrawableRes imageResId: Int,
    @StringRes contentDescriptionResId: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(330.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
                .background(LightGreen)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(id = contentDescriptionResId),
            modifier = Modifier.size(330.dp)
        )
    }
}