package com.example.parcial_pr3_ort.ui.screens.launch

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.LoadingAnimation
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import kotlinx.coroutines.delay

/**
 * Pantalla "Inteligente": Obtiene el ViewModel, observa el estado
 * y maneja la lógica de inicio (LaunchedEffect).
 */
@Composable
fun PasswordChangedScreen(
    navController: NavController,
    wasSuccess: Boolean = true,
    viewModel: PasswordChangeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startChangeProcess(success = wasSuccess)
    }
    LaunchedEffect(state) {
        if (state is PasswordChangeState.Success) {
            delay(2500L)

            navController.navigate(AppRoutes.LOGIN) {
                popUpTo(AppRoutes.AUTH_GRAPH) {
                    inclusive = true
                }
            }
        }
    }

    PasswordChangedContent(
        state = state,
        modifier = modifier
    )
}

/**
 * Pantalla "Tonta": Solo recibe el estado y dibuja la UI.
 * Ideal para Previews y reutilización.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PasswordChangedContent(
    state: PasswordChangeState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedContent(
            targetState = state,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) with
                        fadeOut(animationSpec = tween(300))
            },
            label = "AnimationCircle"
        ) { currentState ->
            LoadingAnimation(state = currentState)
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedContent(
            targetState = state,
            transitionSpec = {
                (slideInVertically { h -> h } + fadeIn()) with
                        (slideOutVertically { h -> -h } + fadeOut())
            },
            label = "AnimationText"
        ) { currentState ->

            val textResId = when (currentState) {
                is PasswordChangeState.Success -> R.string.password_changed_success
                is PasswordChangeState.Error -> R.string.password_changed_error
                else -> null
            }

            if (textResId != null) {
                Text(
                    text = stringResource(id = textResId),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            } else {
                Box(modifier = Modifier.height(60.dp))
            }
        }
    }
}

