package org.example.project.presentation.screens.recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import org.example.project.domain.model.Recipe
import org.example.project.domain.model.RecipeFilter
import org.example.project.domain.usecase.FilterRecipesUseCase
import org.example.project.presentation.components.molecules.RecipeCard
import org.example.project.presentation.components.molecules.RecipeFiltersPanel
import org.example.project.presentation.components.molecules.SearchField
import org.example.project.presentation.screens.recipe_detail.RecipeDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(
    recipes: List<Recipe>,
    categories: List<String>,
    onRecipeSelected: (String) -> Unit,
    selectedRecipeId: String? = null,
    onCloseRecipeDetail: () -> Unit = {},
    onAddToShoppingList: (Recipe) -> String = { "" }
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategories by remember { mutableStateOf<Set<String>>(emptySet()) }
    var maxPrepTime by remember { mutableFloatStateOf(120f) }
    var showFilters by remember { mutableStateOf(false) }
    var isLoadingMore by remember { mutableStateOf(false) }
    var paginationError by remember { mutableStateOf<String?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val listState = rememberLazyListState()
    val recipeDetailSnackbarHostState = remember { SnackbarHostState() }
    val recipeDetailSnackbarScope = rememberCoroutineScope()
    var loadedCount by remember { mutableStateOf(8) }
    val pageSize = 8

    val filteredRecipes = remember(recipes, selectedCategories, maxPrepTime, searchQuery) {
        FilterRecipesUseCase()(
            recipes,
            RecipeFilter(
                searchQuery = searchQuery,
                maxPrepTime = maxPrepTime.toInt(),
                selectedCategories = selectedCategories
            )
        )
    }

    LaunchedEffect(filteredRecipes.size, selectedCategories, maxPrepTime, searchQuery) {
        loadedCount = pageSize
        paginationError = null
        if (filteredRecipes.isNotEmpty() && listState.firstVisibleItemIndex > 0) {
            listState.scrollToItem(0)
        }
    }

    val loadedRecipes = remember(filteredRecipes, loadedCount) {
        filteredRecipes.take(loadedCount.coerceAtMost(filteredRecipes.size))
    }

    val hasMoreRecipes = loadedCount < filteredRecipes.size

    val shouldLoadMore by remember(listState, loadedRecipes.size, hasMoreRecipes, isLoadingMore, paginationError) {
        derivedStateOf {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            hasMoreRecipes &&
                !isLoadingMore &&
                paginationError == null &&
                loadedRecipes.isNotEmpty() &&
                lastVisibleIndex >= (loadedRecipes.lastIndex - 1)
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            isLoadingMore = true
            try {
                // Simulate loading delay (500-1500ms)
                val delay = (500..1500).random().toLong()
                kotlinx.coroutines.delay(delay)
                loadedCount = (loadedCount + pageSize).coerceAtMost(filteredRecipes.size)
                paginationError = null
            } catch (_: CancellationException) {
                throw CancellationException()
            } catch (_: Exception) {
                paginationError = "Błąd podczas ładowania kolejnych przepisów"
            } finally {
                isLoadingMore = false
            }
        }
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
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background).padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SearchField(value = searchQuery, onValueChange = { searchQuery = it }, modifier = Modifier.weight(1f).height(56.dp))

                    IconButton(
                        onClick = { showFilters = !showFilters },
                        modifier = Modifier
                            .height(56.dp)
                            .background(
                                color = if (selectedCategories.isNotEmpty() || maxPrepTime < 120f) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Text("⚙️", fontSize = MaterialTheme.typography.titleLarge.fontSize)
                    }
                }

                Text(
                    text = if (selectedCategories.isEmpty() && maxPrepTime >= 120f) "Wszystkie przepisy" else "Filtry aktywne",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }

            if (loadedRecipes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Nie znaleziono przepisów", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f), textAlign = TextAlign.Center)
                }
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(loadedRecipes, key = { it.id }) { recipe ->
                        RecipeCard(recipe = recipe, onClick = { onRecipeSelected(recipe.id) })
                    }

                    if (hasMoreRecipes && isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    if (paginationError != null) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = paginationError.orEmpty(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.error,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    } else if (!hasMoreRecipes && loadedRecipes.isNotEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Brak więcej przepisów",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    if (hasMoreRecipes && !isLoadingMore) {
                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }

    if (showFilters) {
        ModalBottomSheet(
            onDismissRequest = { showFilters = false },
            sheetState = sheetState
        ) {
            RecipeFiltersPanel(
                categories = categories,
                selectedCategories = selectedCategories,
                onCategorySelected = { category ->
                    selectedCategories = if (category in selectedCategories) selectedCategories - category else selectedCategories + category
                },
                maxPrepTime = maxPrepTime,
                onMaxPrepTimeChange = { maxPrepTime = it },
                onResetFilters = {
                    selectedCategories = emptySet()
                    maxPrepTime = 120f
                }
            )
        }
    }

    val recipeDetailSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    if (selectedRecipeId != null) {
        val selectedRecipe = recipes.firstOrNull { it.id == selectedRecipeId }
        if (selectedRecipe != null) {
            ModalBottomSheet(
                onDismissRequest = onCloseRecipeDetail,
                sheetState = recipeDetailSheetState,
                scrimColor = Color.Black.copy(alpha = 0.32f),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.95f)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        RecipeDetailScreen(
                            recipe = selectedRecipe,
                            onBackClick = onCloseRecipeDetail,
                            onAddToShoppingList = { recipe ->
                                val message = onAddToShoppingList(recipe)
                                recipeDetailSnackbarScope.launch {
                                    recipeDetailSnackbarHostState.showSnackbar(message)
                                }
                            }
                        )

                        SnackbarHost(
                            hostState = recipeDetailSnackbarHostState,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}