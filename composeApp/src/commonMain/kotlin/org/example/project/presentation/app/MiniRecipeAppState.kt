package org.example.project.presentation.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.domain.model.Recipe
import org.example.project.domain.model.ShoppingListItem
import org.example.project.domain.model.UserProfile
import org.example.project.domain.usecase.AddRecipeIngredientsToShoppingListUseCase
import org.example.project.domain.usecase.AddShoppingListItemUseCase
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.GetOnboardingSlidesUseCase
import org.example.project.domain.usecase.GetRecipesUseCase
import org.example.project.domain.usecase.GetUserProfileUseCase
import org.example.project.domain.usecase.ObserveShoppingListUseCase
import org.example.project.domain.usecase.RemoveShoppingListItemUseCase
import org.example.project.domain.usecase.ToggleShoppingListItemUseCase
import org.example.project.domain.usecase.UpdateUserProfileUseCase
import org.example.project.presentation.navigation.BottomNavTab
import org.example.project.presentation.navigation.Screen

class MiniRecipeAppState(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getOnboardingSlidesUseCase: GetOnboardingSlidesUseCase,
    private val observeShoppingListUseCase: ObserveShoppingListUseCase,
    private val addShoppingListItemUseCase: AddShoppingListItemUseCase,
    private val removeShoppingListItemUseCase: RemoveShoppingListItemUseCase,
    private val toggleShoppingListItemUseCase: ToggleShoppingListItemUseCase,
    private val addRecipeIngredientsUseCase: AddRecipeIngredientsToShoppingListUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase
) {
    var currentScreen by mutableStateOf(Screen.ONBOARDING)
        private set

    var selectedRecipeId by mutableStateOf<String?>(null)
        private set

    var recipesToAddToShoppingList by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var shoppingListItems by mutableStateOf(observeShoppingListUseCase())
        private set

    var userProfile by mutableStateOf(getUserProfileUseCase())
        private set

    fun navigateTo(screen: Screen) {
        currentScreen = screen
        selectedRecipeId = null
    }

    fun finishOnboarding() {
        currentScreen = Screen.RECIPES
    }

    fun openRecipe(recipeId: String) {
        selectedRecipeId = recipeId
    }

    fun closeRecipeDetail() {
        selectedRecipeId = null
    }

    fun goBackToRecipes() {
        closeRecipeDetail()
    }

    fun addRecipeToShoppingList(recipe: Recipe): String {
        try {
            if (recipe.ingredients.isEmpty()) {
                return "Ten przepis nie zawiera składników"
            }
            addRecipeIngredientsUseCase(recipe)
            shoppingListItems = observeShoppingListUseCase()
            return "Dodano ${recipe.ingredients.size} składników do listy zakupów"
        } catch (e: Exception) {
            return "Błąd podczas dodawania składników"
        }
    }

    fun clearQueuedShoppingListRecipes() {
        recipesToAddToShoppingList = emptyList()
    }

    fun getRecipes() = getRecipesUseCase()

    fun getCategories() = getCategoriesUseCase()

    fun getOnboardingSlides() = getOnboardingSlidesUseCase()

    fun addShoppingListItem(name: String, quantity: Double, unit: String) {
        addShoppingListItemUseCase(
            ShoppingListItem(
                id = kotlin.random.Random.nextLong().toString(),
                ingredientName = name,
                quantity = quantity,
                unit = unit,
                isChecked = false
            )
        )
        shoppingListItems = observeShoppingListUseCase()
    }

    fun toggleShoppingListItem(itemId: String, isChecked: Boolean) {
        toggleShoppingListItemUseCase(itemId, isChecked)
        shoppingListItems = observeShoppingListUseCase()
    }

    fun removeShoppingListItem(itemId: String) {
        removeShoppingListItemUseCase(itemId)
        shoppingListItems = observeShoppingListUseCase()
    }

    fun updateProfile(profile: UserProfile) {
        updateUserProfileUseCase(profile)
        userProfile = profile
    }

    fun fromCurrentBottomTab(): BottomNavTab? = BottomNavTab.fromScreen(currentScreen)
}