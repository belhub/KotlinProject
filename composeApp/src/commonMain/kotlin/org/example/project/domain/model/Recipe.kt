package org.example.project.domain.model

data class Recipe(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val prepTime: Int = 0,
    val servings: Int = 0,
    val category: String = "",
    val difficulty: String = "średni",
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: List<String> = emptyList(),
    val rating: Float = 5f
)