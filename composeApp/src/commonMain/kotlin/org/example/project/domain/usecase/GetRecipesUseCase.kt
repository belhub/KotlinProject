package org.example.project.domain.usecase

import org.example.project.domain.model.Recipe
import org.example.project.domain.repository.RecipeRepository

class GetRecipesUseCase(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke(): List<Recipe> = recipeRepository.getRecipes()
}