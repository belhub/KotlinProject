package org.example.project.domain.usecase

import org.example.project.domain.model.Recipe
import org.example.project.domain.model.RecipeFilter

class FilterRecipesUseCase {
    operator fun invoke(recipes: List<Recipe>, filter: RecipeFilter): List<Recipe> {
        return recipes.filter { recipe ->
            val matchesSearch = recipe.title.contains(filter.searchQuery, ignoreCase = true)
            val matchesCategory = filter.selectedCategories.isEmpty() || recipe.category in filter.selectedCategories
            val matchesTime = recipe.prepTime <= filter.maxPrepTime

            matchesSearch && matchesCategory && matchesTime
        }
    }
}