package org.example.project.domain.usecase

import org.example.project.domain.model.Recipe
import org.example.project.domain.repository.ShoppingListRepository

class AddRecipeIngredientsToShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    operator fun invoke(recipe: Recipe) {
        shoppingListRepository.addRecipeIngredients(recipe)
    }
}