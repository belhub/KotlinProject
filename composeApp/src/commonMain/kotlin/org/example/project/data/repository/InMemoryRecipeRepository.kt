package org.example.project.data.repository

import org.example.project.data.datasource.StaticRecipeDataSource
import org.example.project.domain.model.OnboardingSlide
import org.example.project.domain.model.Recipe
import org.example.project.domain.repository.RecipeRepository

class InMemoryRecipeRepository : RecipeRepository {
    override fun getRecipes(): List<Recipe> = StaticRecipeDataSource.recipes

    override fun getCategories(): List<String> = StaticRecipeDataSource.categories

    override fun getOnboardingSlides(): List<OnboardingSlide> = StaticRecipeDataSource.onboardingSlides

    override fun getRecipeById(recipeId: String): Recipe? = StaticRecipeDataSource.recipes.firstOrNull { it.id == recipeId }
}