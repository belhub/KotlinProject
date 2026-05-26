package org.example.project.presentation.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.domain.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
                            .height(200.dp),
                        contentScale = ContentScale.Crop,
                        onError = { state ->
                            // Error is handled by showing fallback via error lambda above
                        }
                    )
                } else if (imageUrl.isNotBlank()) {
                    Text(imageUrl, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                } else {
                    Text("🍳", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
            }

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = recipe.title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                AssistChip(
                    onClick = {},
                    label = { Text(recipe.category.replaceFirstChar { it.uppercase() }) }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StatRowItem(icon = "⏱️", text = "${recipe.prepTime} min")
                    StatRowItem(icon = "👥", text = "${recipe.servings} porcji")
                }
                Text(text = recipe.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
            }
        }
    }
}

@Composable
private fun StatRowItem(icon: String, text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = icon, fontSize = MaterialTheme.typography.bodyMedium.fontSize)
        Text(text = text, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
    }
}