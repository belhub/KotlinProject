package org.example.project.domain.model

data class ShoppingListItem(
    val id: String = "",
    val ingredientName: String = "",
    val quantity: Double = 0.0,
    val unit: String = "",
    val isChecked: Boolean = false,
    val recipeId: String? = null
)