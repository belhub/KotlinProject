package org.example.project.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light theme colors - minimalist
val RecipePrimary = Color(0xFF2E7D32) // Fresh green
val RecipePrimaryContainer = Color(0xFFC8E6C9) // Light green
val RecipeSecondary = Color(0xFFD97706) // Warm orange
val RecipeSecondaryContainer = Color(0xFFFED7AA) // Light orange
val RecipeTertiary = Color(0xFF1F2937) // Dark gray for text
val RecipeTertiaryContainer = Color(0xFFF3F4F6) // Very light gray

val RecipeBackground = Color(0xFFFAFAFA) // Almost white
val RecipeSurface = Color(0xFFFFFFFF) // Pure white
val RecipeError = Color(0xFFDC2626) // Red
val RecipeOnError = Color(0xFFFFFFFF) // White text on error
val RecipeErrorContainer = Color(0xFFFEE2E2) // Light red

// Additional colors for the app
val RecipeOnBackground = Color(0xFF1F2937) // Dark text on light background
val RecipeOnSurface = Color(0xFF1F2937) // Dark text
val RecipeOnSecondary = Color(0xFFFFFFFF) // White text
val RecipeOnPrimary = Color(0xFFFFFFFF) // White text
val RecieOnTertiary = Color(0xFFFFFFFF) // White text

val RecipeLightColorScheme = lightColorScheme(
    primary = RecipePrimary,
    onPrimary = RecipeOnPrimary,
    primaryContainer = RecipePrimaryContainer,
    onPrimaryContainer = RecipePrimary,
    secondary = RecipeSecondary,
    onSecondary = RecipeOnSecondary,
    secondaryContainer = RecipeSecondaryContainer,
    onSecondaryContainer = RecipeSecondary,
    tertiary = RecipeTertiary,
    onTertiary = RecieOnTertiary,
    tertiaryContainer = RecipeTertiaryContainer,
    onTertiaryContainer = RecipeTertiary,
    error = RecipeError,
    onError = RecipeOnError,
    errorContainer = RecipeErrorContainer,
    onErrorContainer = RecipeError,
    background = RecipeBackground,
    onBackground = RecipeOnBackground,
    surface = RecipeSurface,
    onSurface = RecipeOnSurface
)

// Neutral colors
val NeutralGray = Color(0xFF6B7280)
val NeutralLightGray = Color(0xFFE5E7EB)
val NeutralDarkGray = Color(0xFF374151)

// Success and other states
val SuccessGreen = Color(0xFF10B981)
val WarningOrange = Color(0xFFF59E0B)
val InfoBlue = Color(0xFF3B82F6)
