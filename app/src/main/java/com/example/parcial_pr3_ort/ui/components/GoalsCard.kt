package com.example.parcial_pr3_ort.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun GoalsCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(357.dp)
            .height(152.dp),
        shape = RoundedCornerShape(31.dp),
        colors = CardDefaults.cardColors(
            containerColor = CaribbeanGreen
        )
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- 1. Sección Izquierda ---
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f), // Ocupa el espacio disponible
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(70.dp)
                ) {

                    CircularProgressIndicator(
                        progress = { 0.50f },
                        modifier = Modifier.size(70.dp),
                        color = OceanBlue,
                        trackColor = Honeydew,
                        strokeWidth = 3.25.dp
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = "Goals Icon",
                        tint = FenceGreen,
                        modifier = Modifier
                            .height(21.dp)
                            .width(37.dp)
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))


                Text(
                    text = "Saving On Goals",
                    style = MaterialTheme.typography.bodyMedium,
                    color = FenceGreen,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }


            Box(
                modifier = Modifier
                    .height(108.dp)
                    .width(1.dp)
                    .background(Honeydew)
            )


            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.salary),
                        contentDescription = "Revenue Icon",
                        tint = FenceGreen,
                        modifier = Modifier.size(width = 31.dp, height = 28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Revenue Last Week",
                            style = MaterialTheme.typography.bodyMedium,
                            color = FenceGreen,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "$4.000.00",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // font-weight: 700 es bold
                            color = FenceGreen,
                            fontSize = 15.sp
                        )
                    }
                }

                // --- LÍNEA DIVISORA HORIZONTAL ---
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .width(161.dp)
                        .background(Honeydew)
                )

                // --- SECCIÓN INFERIOR: FOOD ---
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.food),
                        contentDescription = "Food Icon",
                        tint = FenceGreen,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(width = 19.dp, height = 34.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = "Food Last Week",
                            style = MaterialTheme.typography.bodyMedium,
                            color = FenceGreen,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "-$100.00",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), // font-weight: 700 es bold
                            color = OceanBlue,
                            fontSize = 15.sp
                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GoalsCardPreview() {
    PARCIALPR3ORTTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                GoalsCard()
            }
        }
    }
}
