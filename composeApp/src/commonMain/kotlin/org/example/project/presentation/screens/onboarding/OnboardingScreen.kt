package org.example.project.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.domain.model.OnboardingSlide

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    slides: List<OnboardingSlide>,
    onFinished: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { slides.size })

    // No automatic navigation - only when user explicitly clicks continue on last slide
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) { page ->
                OnboardingSlideContent(
                    slide = slides[page],
                    isLastSlide = page == slides.lastIndex,
                    onContinue = if (page == slides.lastIndex) onFinished else null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                )
            }

            OnboardingIndicators(
                totalSlides = slides.size,
                currentSlide = pagerState.currentPage,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }
}

@Composable
private fun OnboardingSlideContent(
    slide: OnboardingSlide,
    isLastSlide: Boolean = false,
    onContinue: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = slide.icon, fontSize = MaterialTheme.typography.headlineSmall.fontSize, modifier = Modifier.padding(bottom = 32.dp))
            Text(text = slide.title, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 16.dp), color = MaterialTheme.colorScheme.onBackground)
            Text(text = slide.description, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 32.dp), color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                slide.features.forEach { feature ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "✓", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                        Text(text = feature, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }
        }

        if (isLastSlide && onContinue != null) {
            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rozpocznij")
            }
        }
    }
}

@Composable
private fun OnboardingIndicators(
    totalSlides: Int,
    currentSlide: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        repeat(totalSlides) { index ->
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .size(if (index == currentSlide) 12.dp else 8.dp)
                    .background(
                        color = if (index == currentSlide) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

