package org.example.project.domain.usecase

import org.example.project.domain.model.ShoppingListItem
import org.example.project.domain.repository.ShoppingListRepository

class ObserveShoppingListUseCase(
    private val shoppingListRepository: ShoppingListRepository
) {
    operator fun invoke(): List<ShoppingListItem> = shoppingListRepository.getItems()
}