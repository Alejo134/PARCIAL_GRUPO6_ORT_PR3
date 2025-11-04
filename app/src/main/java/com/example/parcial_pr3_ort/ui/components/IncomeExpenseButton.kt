@file:Suppress("FunctionName")

package com.example.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

// ---- API de colores
data class StatButtonColors(
    val container: Color,
    val content: Color,
    val containerSelected: Color,
    val contentSelected: Color,
)

object StatButtonDefaults {
    @Composable
    fun colors(
        container: Color = MaterialTheme.colorScheme.surface,
        content: Color = MaterialTheme.colorScheme.onSurface,
        containerSelected: Color = MaterialTheme.colorScheme.primary,
        contentSelected: Color = MaterialTheme.colorScheme.onPrimary,
    ) = StatButtonColors(
        container, content, containerSelected, contentSelected
    )

    val Shape = RoundedCornerShape(24.dp)
}

/**
 * Botón tipo “stat card”.
 *
 * @param iconRes     recurso del ícono (p.ej. R.drawable.ic_arrow_diagonal)
 * @param title       texto del medio
 * @param value       texto de abajo
 * @param selected    estado externo (controlado)
 * @param onClick     callback de click
 * @param width/height tamaño (default 171×101 dp)
 * @param colors      esquema de colores para normal/seleccionado
 */
@Composable
fun StatButton(
    @DrawableRes iconRes: Int,
    title: String,
    value: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 171.dp,
    height: Dp = 101.dp,
    colors: StatButtonColors = StatButtonDefaults.colors(),
) {
    val containerColor by animateColorAsState(
        if (selected) colors.containerSelected else colors.container,
        label = "containerColor"
    )
    val contentColor by animateColorAsState(
        if (selected) colors.contentSelected else colors.content,
        label = "contentColor"
    )

    Card(
        modifier = modifier
            .size(width = width, height = height)
            .clickable { onClick() },
        shape = StatButtonDefaults.Shape,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(
            width = 0.dp,
            color = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(2.dp, contentColor),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = null,
                            tint = contentColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Text(
                text = title,
                color = contentColor.copy(alpha = 0.9f),
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = value,
                color = contentColor,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StatButtonPreview() {
    PARCIALPR3ORTTheme {
        var selected by remember { mutableStateOf(false) }

        Surface {
            StatButton(
                iconRes = R.drawable.check,
                title = "Income",
                value = "$4,120.00",
                selected = selected,
                onClick = { selected = !selected },
                colors = StatButtonDefaults.colors(
                    container = MaterialTheme.colorScheme.surface,
                    content = MaterialTheme.colorScheme.primary,        // color normal
                    containerSelected = MaterialTheme.colorScheme.primaryContainer,
                    contentSelected = MaterialTheme.colorScheme.onPrimaryContainer))
        }
    }
}
