package org.example.project.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.domain.model.UserProfile
import org.example.project.presentation.components.molecules.HomeActionCard

@Composable
fun HomeScreen(
    userProfile: UserProfile,
    onBrowseRecipes: () -> Unit,
    onSearchRecipes: () -> Unit,
    onShoppingList: () -> Unit,
    onProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) {
            Text(text = "Witaj, ${userProfile.name}! 👋", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Odkryj nowe przepisy i zarządzaj swoją listą zakupów", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
        }

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            HomeActionCard(icon = "🍽️", title = "Przeglądaj przepisy", description = "Odkryj tysięce smacznych dań", onClick = onBrowseRecipes)
            HomeActionCard(icon = "🔍", title = "Szukaj i filtruj", description = "Znajdź idealne danie dla siebie", onClick = onSearchRecipes)
            HomeActionCard(icon = "🛒", title = "Zarządzaj listą zakupów", description = "Nie zapomnij ważnych składników", onClick = onShoppingList)
            HomeActionCard(icon = "👤", title = "Edytuj profil", description = "Zarządzaj swoimi informacjami", onClick = onProfile)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Ostatnio przeglądane", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(bottom = 12.dp))

        Card(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Brak ostatnio przeglądanych przepisów", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f), textAlign = TextAlign.Center)
            }
        }
    }
}