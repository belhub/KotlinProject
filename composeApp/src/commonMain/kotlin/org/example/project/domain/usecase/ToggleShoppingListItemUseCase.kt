package org.example.project.domain.usecase

import org.example.project.domain.repository.ShoppingListRepository

class ToggleShoppingListItemUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    operator fun invoke(itemId: String, isChecked: Boolean) {
        shoppingListRepository.updateItem(itemId, isChecked)
    }
}