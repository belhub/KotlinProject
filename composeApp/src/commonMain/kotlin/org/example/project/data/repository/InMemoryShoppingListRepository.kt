package org.example.project.data.repository

import org.example.project.data.datasource.StaticRecipeDataSource
import org.example.project.domain.model.Recipe
import org.example.project.domain.model.ShoppingListItem
import org.example.project.domain.repository.ShoppingListRepository

class InMemoryShoppingListRepository : ShoppingListRepository {
    private val items = StaticRecipeDataSource.shoppingListItems.toMutableList()

    override fun getItems(): List<ShoppingListItem> = items.toList()

    override fun addItem(item: ShoppingListItem) {
        items.add(item)
    }

    override fun updateItem(itemId: String, isChecked: Boolean) {
        val index = items.indexOfFirst { it.id == itemId }
        if (index >= 0) {
            items[index] = items[index].copy(isChecked = isChecked)
        }
    }

    override fun removeItem(itemId: String) {
        items.removeAll { it.id == itemId }
    }

    override fun addRecipeIngredients(recipe: Recipe) {
        recipe.ingredients.forEach { ingredient ->
            val normalizedName = ingredient.name.trim()
            val normalizedUnit = ingredient.unit.trim().ifBlank { "szt." }
            val normalizedQuantity = ingredient.quantity.takeIf { it.isFinite() && it > 0.0 } ?: 1.0

            if (normalizedName.isBlank()) {
                return@forEach
            }

            if (items.none { it.ingredientName.equals(normalizedName, ignoreCase = true) }) {
                items.add(
                    ShoppingListItem(
                        id = "${recipe.id}-${ingredient.id}-${kotlin.random.Random.nextLong().toString(16)}",
                        ingredientName = normalizedName,
                        quantity = normalizedQuantity,
                        unit = normalizedUnit,
                        isChecked = false,
                        recipeId = recipe.id
                    )
                )
            }
        }
    }
}