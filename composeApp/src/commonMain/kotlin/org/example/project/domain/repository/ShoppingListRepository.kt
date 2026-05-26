package org.example.project.domain.repository

import org.example.project.domain.model.Recipe
import org.example.project.domain.model.ShoppingListItem

interface ShoppingListRepository {
    fun getItems(): List<ShoppingListItem>
    fun addItem(item: ShoppingListItem)
    fun updateItem(itemId: String, isChecked: Boolean)
    fun removeItem(itemId: String)
    fun addRecipeIngredients(recipe: Recipe)
}