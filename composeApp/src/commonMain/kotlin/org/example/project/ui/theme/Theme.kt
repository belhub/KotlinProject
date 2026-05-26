package org.example.project.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun RecipeAppTheme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = RecipeLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = RecipeAppTypography,
        content = content
    )
}
