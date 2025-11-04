package com.example.parcial_pr3_ort.ui.screens.launch

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial_pr3_ort.R
import com.example.parcial_pr3_ort.ui.components.ImageWithCircleBackground
import com.example.parcial_pr3_ort.ui.components.OnboardingScreenLayout
import com.example.parcial_pr3_ort.ui.components.PageIndicator
import com.example.parcial_pr3_ort.ui.screens.AppRoutes
import com.example.parcial_pr3_ort.ui.theme.Cyprus
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import kotlinx.coroutines.launch

private data class OnboardingPage(
    @StringRes val textResId: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val imageDescResId: Int
)

private val onboardingPages = listOf(
    OnboardingPage(
        textResId = R.string.onboarding_welcome,
        imageResId = R.drawable.onboarding_a_image,
        imageDescResId = R.string.onboarding_image_coins_desc
    ),
    OnboardingPage(
        textResId = R.string.onboarding_control,
        imageResId = R.drawable.onboarding_b_image,
        imageDescResId = R.string.onboarding_image_phone_desc
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {

    val pagerState = rememberPagerState { onboardingPages.size }
    val scope = rememberCoroutineScope()
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->

        val page = onboardingPages[pageIndex]


        OnboardingScreenLayout(
            topContent = {
                Text(
                    text = stringResource(id = page.textResId),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            },

            bottomContent = {
                ImageWithCircleBackground(
                    imageResId = page.imageResId,
                    contentDescriptionResId = page.imageDescResId
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(id = R.string.onboarding_next),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Cyprus,
                    modifier = Modifier.clickable {
                        scope.launch {
                            if (pagerState.currentPage < onboardingPages.lastIndex) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                navController.navigate(AppRoutes.SECONDARY_LAUNCH) {
                                    popUpTo(AppRoutes.ONBOARDING) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PageIndicator(
                    pageCount = onboardingPages.size,
                    currentPage = pagerState.currentPage
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        )
    }
}


