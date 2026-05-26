package org.example.project.presentation.navigation

enum class BottomNavTab {
    RECIPES,
    SHOPPING_LIST,
    SETTINGS;

    companion object {
        fun fromScreen(screen: Screen): BottomNavTab? = when (screen) {
            Screen.ONBOARDING -> null
            Screen.RECIPES, Screen.RECIPE_DETAIL -> RECIPES
            Screen.SHOPPING_LIST -> SHOPPING_LIST
            Screen.SETTINGS -> SETTINGS
        }
    }
}