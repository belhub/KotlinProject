package org.example.project.domain.usecase

import org.example.project.domain.model.OnboardingSlide
import org.example.project.domain.repository.RecipeRepository

class GetOnboardingSlidesUseCase(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(): List<OnboardingSlide> = recipeRepository.getOnboardingSlides()
}