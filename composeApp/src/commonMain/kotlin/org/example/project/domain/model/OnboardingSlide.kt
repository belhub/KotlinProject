package org.example.project.domain.model

data class OnboardingSlide(
    val title: String = "",
    val description: String = "",
    val icon: String = "",
    val features: List<String> = emptyList()
)