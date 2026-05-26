package org.example.project.presentation.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.presentation.components.organisms.RecipeAppBottomNavBar
import org.example.project.presentation.navigation.Screen
import org.example.project.presentation.screens.onboarding.OnboardingScreen
import org.example.project.presentation.screens.recipe_detail.RecipeDetailScreen
import org.example.project.presentation.screens.recipes.RecipesScreen
import org.example.project.presentation.screens.settings.SettingsScreen
import org.example.project.presentation.screens.shopping.ShoppingListScreen
import org.example.project.ui.theme.RecipeAppTheme

@Composable
@Preview
fun MiniRecipeApp() {
    RecipeAppTheme {
        val appState = androidx.compose.runtime.remember { MiniRecipeAppContainer.createAppState() }

        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    if (appState.currentScreen != Screen.ONBOARDING) {
                        RecipeAppBottomNavBar(
                            currentScreen = appState.currentScreen,
                            onNavigate = appState::navigateTo
                        )
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(WindowInsets.statusBars.asPaddingValues())
                ) {
                    when (appState.currentScreen) {
                        Screen.ONBOARDING -> OnboardingScreen(
                            slides = appState.getOnboardingSlides(),
                            onFinished = appState::finishOnboarding
                        )

                        Screen.RECIPES -> RecipesScreen(
                            recipes = appState.getRecipes(),
                            categories = appState.getCategories(),
                            onRecipeSelected = appState::openRecipe,
                            selectedRecipeId = appState.selectedRecipeId,
                            onCloseRecipeDetail = appState::closeRecipeDetail,
                            onAddToShoppingList = appState::addRecipeToShoppingList
                        )

                        Screen.SHOPPING_LIST -> ShoppingListScreen(
                            items = appState.shoppingListItems,
                            recipesToAdd = appState.recipesToAddToShoppingList,
                            onCheckedChange = appState::toggleShoppingListItem,
                            onRemove = appState::removeShoppingListItem,
                            onAddItem = appState::addShoppingListItem,
                            onQueueCleared = appState::clearQueuedShoppingListRecipes
                        )

                        Screen.SETTINGS -> SettingsScreen(
                            userProfile = appState.userProfile,
                            onProfileChange = appState::updateProfile
                        )

                        Screen.RECIPE_DETAIL -> {} // Recipe detail is shown as modal in RecipesScreen
                    }
                }
            }

        }
    }
}