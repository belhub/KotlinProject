package org.example.project.domain.repository

import org.example.project.domain.model.OnboardingSlide
import org.example.project.domain.model.Recipe

interface RecipeRepository {
    fun getRecipes(): List<Recipe>
    fun getCategories(): List<String>
    fun getOnboardingSlides(): List<OnboardingSlide>
    fun getRecipeById(recipeId: String): Recipe?
}