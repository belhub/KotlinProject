package org.example.project.domain.usecase

import org.example.project.domain.repository.ShoppingListRepository

class RemoveShoppingListItemUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    operator fun invoke(itemId: String) {
        shoppingListRepository.removeItem(itemId)
    }
}