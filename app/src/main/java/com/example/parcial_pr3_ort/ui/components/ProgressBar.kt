package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.FenceGreen
import com.example.parcial_pr3_ort.ui.theme.Honeydew
import com.example.parcial_pr3_ort.ui.theme.OceanBlue
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

@Composable
fun ProgressBar(
    categoryName: String,
    currentValue: Double,
    modifier: Modifier = Modifier,
    colorProgressBar: Color,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenedor principal para la barra de progreso con textos superpuestos
        Box(
            modifier = Modifier
                .width(330.dp)
                .height(27.dp)
                .clip(CircleShape) // Bordes redondeados perfectos (pill-shape)
                .background(FenceGreen) // Color de fondo de la barra
        ) {

            // Barra de progreso activa (la que muestra el avance)
            Box(
                modifier = Modifier
                    .width(261.dp) // El ancho depende del progreso
                    .height(27.dp)
                    .clip(CircleShape)
                    .background(colorProgressBar)
                    .align(Alignment.CenterEnd)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Alinea los textos en los extremos
            ) {
                Text(
                    text = "30%",
                    color = Honeydew,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$ ${"%,.2f".format(currentValue)}",
                    color = FenceGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "check icon",
                tint = FenceGreen,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = categoryName,
                color = FenceGreen,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}


// --- Preview para visualizar el componente ---
@Preview(showBackground = true)
@Composable
fun CategoryProgressBarPreview() {
    PARCIALPR3ORTTheme {
        Surface(
            color = CaribbeanGreen,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                ProgressBar(
                    categoryName = "30% of your expenses, looks good.",
                    currentValue = 20000.0,
                    colorProgressBar = OceanBlue
                )
            }
        }
    }
}
