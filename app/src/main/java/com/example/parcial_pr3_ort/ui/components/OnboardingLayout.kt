package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

/**
 * Layout base reutilizable.
 * Provee un slot para el contenido superior y
 * un slot para el contenido inferior (fondo blanco redondeado).
 *
 * @param topScreenWeight El "peso" (de 0.0 a 1.0) que ocupa la parte superior.
 * El resto se asigna a la parte inferior.
 */
@Composable
fun OnboardingScreenLayout(
    topScreenWeight: Float = 0.4f,
    topContent: @Composable BoxScope.() -> Unit,
    bottomContent: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(topScreenWeight)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            topContent()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f - topScreenWeight)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            bottomContent()
        }
    }
}