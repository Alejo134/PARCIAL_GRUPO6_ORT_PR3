package com.example.parcial_pr3_ort.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

/**
 * Un componente de botón simple que muestra un ícono y un texto debajo.
 *
 * @param icon El Painter del ícono a mostrar.
 * @param name El texto que se mostrará debajo del ícono.
 * @param onClick La acción a ejecutar cuando se presiona el botón.
 * @param modifier El modificador para aplicar al componente.
 */
@Composable
fun ItemCategories(
    icon: Painter,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: Painter,
    isSelected: Boolean
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Crossfade(
            targetState = isSelected,
            animationSpec = tween(durationMillis = 300),
            label = "IconCrossfade"
        ) { isCurrentlySelected ->

            val currentIcon = if (isCurrentlySelected) selectedIcon else icon

            Image(
                painter = currentIcon,
                contentDescription = name,
                modifier = Modifier
                    .size(width = 105.dp, height = 98.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClick() },
                contentScale = ContentScale.Crop
            )
        }

            // 6. Un pequeño espacio entre el ícono y el texto.
            Spacer(modifier = Modifier.height(8.dp))

            // 7. El Texto.
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium, // Usa un estilo del tema
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center, // Asegura centrado si el texto es muy largo
                color = MaterialTheme.colorScheme.onSurface // Color que contrasta con el fondo
            )
        }
    }


/**
 * Preview para visualizar el componente `ItemCategories` de forma aislada.
 */
@Preview(showBackground = true)
@Composable
fun ItemCategoriesPreview() {
    PARCIALPR3ORTTheme {
        ItemCategories(

            icon = painterResource(id = R.drawable.foodcategory),
            name = "Food",
            onClick = {
            },
            isSelected = false,
            selectedIcon = painterResource(id = R.drawable.foodcategory_selected)
        )
    }
}
