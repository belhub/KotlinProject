package org.example.project.domain.usecase

import org.example.project.domain.repository.RecipeRepository

class GetCategoriesUseCase(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(): List<String> = recipeRepository.getCategories()
}