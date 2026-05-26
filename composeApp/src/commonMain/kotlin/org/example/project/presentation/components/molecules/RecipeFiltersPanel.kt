package org.example.project.presentation.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeFiltersPanel(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (String) -> Unit,
    maxPrepTime: Float,
    onMaxPrepTimeChange: (Float) -> Unit,
    onResetFilters: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Czas przygotowania: ${maxPrepTime.toInt()} minut", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Slider(
                value = maxPrepTime,
                onValueChange = onMaxPrepTimeChange,
                valueRange = 5f..180f,
                steps = 35,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.primary, activeTrackColor = MaterialTheme.colorScheme.primary)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Typ dania", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    FilterChip(
                        selected = category in selectedCategories,
                        onClick = { onCategorySelected(category) },
                        label = { Text(category.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.labelMedium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }

        Button(
            onClick = onResetFilters,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            shape = RoundedCornerShape(999.dp)
        ) {
            Text("Resetuj filtry", style = MaterialTheme.typography.labelMedium)
        }
    }
}