package com.example.parcial_pr3_ort.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.LightGreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

// Enum para representar los estados posibles
enum class TimePeriod {
    Daily, Weekly, Monthly
}

@Composable
fun PeriodSelector(
    modifier: Modifier = Modifier,
    onPeriodSelected: (TimePeriod) -> Unit,
    // 🔧 Parche: nudges por período (valores negativos = mover a la izquierda)
    dailyNudge: Dp = (0).dp,
    weeklyNudge: Dp = (0).dp,
    monthlyNudge: Dp = (0).dp,
    currentPeriod: TimePeriod
) {
    var selectedPeriod by remember { mutableStateOf(TimePeriod.Weekly) }
    val options = TimePeriod.values()

    val containerWidth = 358.dp
    val padding = 5.dp
    val availableWidth = containerWidth - (padding * 2)
    val cellWidth = availableWidth / options.size

    // Offset base “ideal” (fraccional, sin acumulación)
    val baseOffset = availableWidth * (selectedPeriod.ordinal / options.size.toFloat())

    // 🔧 Aplica el nudge según período
    val nudge = when (selectedPeriod) {
        TimePeriod.Daily -> dailyNudge
        TimePeriod.Weekly -> weeklyNudge
        TimePeriod.Monthly -> monthlyNudge
    }

    val animatedOffset by animateDpAsState(
        targetValue = baseOffset + nudge,
        animationSpec = tween(durationMillis = 300),
        label = "offsetAnimation"
    )

    Box(
        modifier = modifier
            .width(containerWidth)
            .height(60.dp)
            .clip(RoundedCornerShape(22.dp))
            .padding(padding)
            .background(LightGreen)
    ) {
        // Pastilla
        Box(
            modifier = Modifier
                .width(cellWidth)
                .height(50.dp)
                .offset(x = animatedOffset)
                .clip(RoundedCornerShape(19.dp))
                .background(CaribbeanGreen)
        )

        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { period ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            selectedPeriod = period
                            onPeriodSelected(period)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = period.name,
                        color = FenceGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// --- Preview para visualizar el componente ---
@Preview(showBackground = true)
@Composable
fun PeriodSelectorPreview() {
    PARCIALPR3ORTTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(
                modifier = Modifier.padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                PeriodSelector(
                    onPeriodSelected = { period ->
                        println("Periodo seleccionado: $period")
                    },
                    currentPeriod = TimePeriod.Weekly
                )
            }
        }
    }
}
