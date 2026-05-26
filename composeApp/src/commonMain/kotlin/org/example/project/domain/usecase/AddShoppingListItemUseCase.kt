package org.example.project.domain.usecase

import org.example.project.domain.model.ShoppingListItem
import org.example.project.domain.repository.ShoppingListRepository

class AddShoppingListItemUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    operator fun invoke(item: ShoppingListItem) {
        shoppingListRepository.addItem(item)
    }
}