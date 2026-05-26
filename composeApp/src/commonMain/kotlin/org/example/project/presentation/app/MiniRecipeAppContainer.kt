package org.example.project.presentation.app

import org.example.project.data.repository.InMemoryRecipeRepository
import org.example.project.data.repository.InMemoryShoppingListRepository
import org.example.project.data.repository.InMemoryUserProfileRepository
import org.example.project.domain.usecase.AddRecipeIngredientsToShoppingListUseCase
import org.example.project.domain.usecase.AddShoppingListItemUseCase
import org.example.project.domain.usecase.FilterRecipesUseCase
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.GetOnboardingSlidesUseCase
import org.example.project.domain.usecase.GetRecipesUseCase
import org.example.project.domain.usecase.GetUserProfileUseCase
import org.example.project.domain.usecase.ObserveShoppingListUseCase
import org.example.project.domain.usecase.RemoveShoppingListItemUseCase
import org.example.project.domain.usecase.ToggleShoppingListItemUseCase
import org.example.project.domain.usecase.UpdateUserProfileUseCase

object MiniRecipeAppContainer {
    private val recipeRepository = InMemoryRecipeRepository()
    private val shoppingListRepository = InMemoryShoppingListRepository()
    private val userProfileRepository = InMemoryUserProfileRepository()

    private val getRecipesUseCase = GetRecipesUseCase(recipeRepository)
    private val getCategoriesUseCase = GetCategoriesUseCase(recipeRepository)
    private val getOnboardingSlidesUseCase = GetOnboardingSlidesUseCase(recipeRepository)
    private val observeShoppingListUseCase = ObserveShoppingListUseCase(shoppingListRepository)
    private val addShoppingListItemUseCase = AddShoppingListItemUseCase(shoppingListRepository)
    private val removeShoppingListItemUseCase = RemoveShoppingListItemUseCase(shoppingListRepository)
    private val toggleShoppingListItemUseCase = ToggleShoppingListItemUseCase(shoppingListRepository)
    private val addRecipeIngredientsUseCase = AddRecipeIngredientsToShoppingListUseCase(shoppingListRepository)
    private val getUserProfileUseCase = GetUserProfileUseCase(userProfileRepository)
    private val updateUserProfileUseCase = UpdateUserProfileUseCase(userProfileRepository)

    fun createAppState(): MiniRecipeAppState {
        return MiniRecipeAppState(
            getRecipesUseCase = getRecipesUseCase,
            getCategoriesUseCase = getCategoriesUseCase,
            getOnboardingSlidesUseCase = getOnboardingSlidesUseCase,
            observeShoppingListUseCase = observeShoppingListUseCase,
            addShoppingListItemUseCase = addShoppingListItemUseCase,
            removeShoppingListItemUseCase = removeShoppingListItemUseCase,
            toggleShoppingListItemUseCase = toggleShoppingListItemUseCase,
            addRecipeIngredientsUseCase = addRecipeIngredientsUseCase,
            getUserProfileUseCase = getUserProfileUseCase,
            updateUserProfileUseCase = updateUserProfileUseCase
        )
    }

    fun filterRecipesUseCase(): FilterRecipesUseCase = FilterRecipesUseCase()
}