package com.example.parcial_pr3_ort.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.screens.launch.PasswordChangeState


@Composable
fun LoadingAnimation(
    state: PasswordChangeState,
    modifier: Modifier = Modifier
) {
    val (iconResId, descResId) = when (state) {
        PasswordChangeState.LoadingCheck1 -> R.drawable.check_uno to R.string.loading_animation_desc
        PasswordChangeState.LoadingCheck2 -> R.drawable.check_dos to R.string.loading_animation_desc
        PasswordChangeState.LoadingCheck3 -> R.drawable.check_tres to R.string.loading_animation_desc
        PasswordChangeState.Success -> R.drawable.check_complete to R.string.success_animation_desc

        PasswordChangeState.LoadingDenied1 -> R.drawable.denied_uno to R.string.loading_animation_desc
        PasswordChangeState.LoadingDenied2 -> R.drawable.denied_dos to R.string.loading_animation_desc
        PasswordChangeState.LoadingDenied3 -> R.drawable.denied_tres to R.string.loading_animation_desc
        PasswordChangeState.Error -> R.drawable.denied_complete to R.string.error_animation_desc
    }

    Surface(
        shape = CircleShape,
        modifier = modifier.size(160.dp),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = stringResource(id = descResId),
                modifier = Modifier.fillMaxSize(0.9f),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}