package org.example.project.presentation.screens.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
    onBackClick: () -> Unit,
    onAddToShoppingList: (Recipe) -> Unit
) {
    if (recipe == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Przepis nie znaleziony")
        }
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("Wróć")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                val imageUrl = recipe.imageUrl.trim()
                val isRemoteUrl = imageUrl.startsWith("https://") || imageUrl.startsWith("http://")

                if (isRemoteUrl) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = recipe.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop,
                        onError = { _ ->
                            // Error state: let AsyncImage handle fallback
                        }
                    )
                } else if (imageUrl.isNotBlank()) {
                    Text(imageUrl, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                } else {
                    Text("🍳", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
            }

            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = recipe.title, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground)
                    Text(text = recipe.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    MetricCard(label = "Czas", value = "${recipe.prepTime} min", modifier = Modifier.weight(1f))
                    MetricCard(label = "Porcje", value = recipe.servings.toString(), modifier = Modifier.weight(1f))
                    MetricCard(label = "Trudność", value = recipe.difficulty, modifier = Modifier.weight(1f))
                }

                Text("Składniki", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                recipe.ingredients.forEach { ingredient ->
                    Text("• ${ingredient.name} - ${ingredient.quantity} ${ingredient.unit}", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f))
                }

                Text("Instrukcje", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                recipe.instructions.forEachIndexed { index, step ->
                    Text("${index + 1}. $step", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f))
                }

                Button(onClick = { onAddToShoppingList(recipe) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Dodaj składniki do listy zakupów")
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(88.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
            Text(text = value, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}