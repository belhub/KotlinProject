package org.example.project.presentation.screens.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.domain.model.Recipe
import org.example.project.domain.model.ShoppingListItem

@Composable
fun ShoppingListScreen(
    items: List<ShoppingListItem>,
    recipesToAdd: List<Recipe>,
    onCheckedChange: (String, Boolean) -> Unit,
    onRemove: (String) -> Unit,
    onAddItem: (String, Double, String) -> Unit,
    onQueueCleared: () -> Unit
) {
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("") }
    var newItemUnit by remember { mutableStateOf("szt.") }
    var unitMenuExpanded by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(recipesToAdd) {
        if (recipesToAdd.isNotEmpty()) {
            onQueueCleared()
        }
    }

    val activeItems = items.filterNot { it.isChecked }
    val purchasedItems = items.filter { it.isChecked }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).background(MaterialTheme.colorScheme.background)) {
            if (items.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Lista jest pusta\nDodaj składniki z przepisów lub ręcznie", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f), textAlign = TextAlign.Center)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Header removed intentionally — content starts below status bar without a top AppBar

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                        ) {
                            Column(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(text = "Dodaj składnik", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)

                                // Row 1: Name
                                OutlinedTextField(
                                    value = newItemName,
                                    onValueChange = {
                                        newItemName = it
                                        if (it.isNotBlank()) nameError = null
                                    },
                                    label = { Text("Nazwa") },
                                    modifier = Modifier.fillMaxWidth(),
                                    singleLine = true,
                                    isError = nameError != null
                                )
                                if (nameError != null) {
                                    Text(text = nameError.orEmpty(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                                }

                                // Row 2: Quantity + Unit
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = newItemQuantity,
                                        onValueChange = { nextValue ->
                                            if (nextValue.isEmpty() || nextValue.matches(Regex("^\\d*(?:[\\.,]\\d*)?$"))) {
                                                newItemQuantity = nextValue
                                                if (nextValue.isNotBlank() && nextValue.toDoubleOrNull() != null) {
                                                    quantityError = null
                                                }
                                            }
                                        },
                                        label = { Text("Ilość") },
                                        modifier = Modifier.weight(1f),
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                        isError = quantityError != null
                                    )

                                    Box(modifier = Modifier.weight(0.8f)) {
                                        OutlinedTextField(
                                            value = newItemUnit,
                                            onValueChange = {},
                                            modifier = Modifier.fillMaxWidth(),
                                            label = { Text("Jedn.") },
                                            singleLine = true,
                                            readOnly = true
                                        )
                                        TextButton(
                                            onClick = { unitMenuExpanded = true },
                                            modifier = Modifier.align(Alignment.CenterEnd),
                                            contentPadding = PaddingValues(0.dp)
                                        ) {
                                            Text("▼", style = MaterialTheme.typography.labelSmall)
                                        }
                                        DropdownMenu(
                                            expanded = unitMenuExpanded,
                                            onDismissRequest = { unitMenuExpanded = false }
                                        ) {
                                            listOf("szt.", "g", "kg", "ml", "l").forEach { unit ->
                                                DropdownMenuItem(
                                                    text = { Text(unit) },
                                                    onClick = {
                                                        newItemUnit = unit
                                                        unitMenuExpanded = false
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                                if (quantityError != null) {
                                    Text(text = quantityError.orEmpty(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
                                }

                                Button(
                                    onClick = {
                                        val nameIsValid = newItemName.isNotBlank()
                                        val quantityValue = newItemQuantity.toDoubleOrNull()

                                        nameError = if (nameIsValid) null else "Nazwa jest wymagana"
                                        quantityError = when {
                                            newItemQuantity.isBlank() -> "Ilość jest wymagana"
                                            quantityValue == null -> "Ilość musi być liczbą"
                                            else -> null
                                        }

                                        if (nameError == null && quantityError == null && quantityValue != null) {
                                            onAddItem(newItemName.trim(), quantityValue, newItemUnit)
                                            newItemName = ""
                                            newItemQuantity = ""
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("Dodaj")
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    if (activeItems.isNotEmpty()) {
                        item {
                            Text(
                                text = "Do kupienia",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 4.dp)
                            )
                        }
                        items(activeItems, key = { it.id }) { item ->
                            ShoppingListItemRow(
                                item = item,
                                onCheckedChange = { checked -> onCheckedChange(item.id, checked) },
                                onRemove = { onRemove(item.id) }
                            )
                        }
                    }

                    if (purchasedItems.isNotEmpty()) {
                        if (activeItems.isNotEmpty()) {
                            item {
                                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            }
                        }
                        item {
                            Text(
                                text = "Zakupione",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 4.dp)
                            )
                        }
                        items(purchasedItems, key = { it.id }) { item ->
                            ShoppingListItemRow(
                                item = item,
                                onCheckedChange = { checked -> onCheckedChange(item.id, checked) },
                                onRemove = { onRemove(item.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShoppingListItemRow(
    item: ShoppingListItem,
    onCheckedChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = if (item.isChecked) MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5f) else MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Checkbox(checked = item.isChecked, onCheckedChange = onCheckedChange, colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = item.ingredientName, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (item.isChecked) 0.45f else 1f))
                if (item.quantity > 0) {
                    Text(text = "${item.quantity} ${item.unit}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                }
            }
            IconButton(onClick = onRemove, modifier = Modifier.height(32.dp)) { Text("❌") }
        }
    }
}