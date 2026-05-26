package org.example.project.domain.model

data class RecipeFilter(
    val maxPrepTime: Int = 120,
    val selectedCategories: Set<String> = emptySet(),
    val searchQuery: String = ""
)