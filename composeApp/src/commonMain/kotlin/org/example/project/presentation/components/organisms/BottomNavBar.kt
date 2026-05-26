package org.example.project.presentation.components.organisms

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.example.project.presentation.navigation.BottomNavTab
import org.example.project.presentation.navigation.Screen

@Composable
fun RecipeAppBottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val navItems = listOf(
        NavItem(BottomNavTab.RECIPES, "Przepisy", "🍳", Screen.RECIPES),
        NavItem(BottomNavTab.SHOPPING_LIST, "Lista zakupów", "🛒", Screen.SHOPPING_LIST),
        NavItem(BottomNavTab.SETTINGS, "Ustawienia", "⚙️", Screen.SETTINGS)
    )

    NavigationBar {
        navItems.forEach { item ->
            val isSelected = BottomNavTab.fromScreen(currentScreen) == item.tab
            NavigationBarItem(
                icon = { Text(item.icon) },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        onNavigate(item.targetScreen)
                    }
                }
            )
        }
    }
}

private data class NavItem(
    val tab: BottomNavTab,
    val label: String,
    val icon: String,
    val targetScreen: Screen
)