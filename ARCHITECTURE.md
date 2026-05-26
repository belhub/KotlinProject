# Mini-Recipe App - Dokumentacja Projektu

## 📱 Przegląd Aplikacji

Mini-Recipe App to nowoczesna aplikacja mobilna napisana w **Kotlin Multiplatform** (Compose), która pozwala użytkownikom:

- 🥘 Przeglądać szeroki katalog przepisów
- 🔍 Wyszukiwać i filtrować dania
- 🛒 Zarządzać listą zakupów
- 👤 Edytować swój profil

---

## 🏗️ Architektura Projektu

### Struktura Katalogów

```
composeApp/src/commonMain/kotlin/org/example/project/
├── data/
│   ├── Models.kt              # Modele danych (Recipe, Ingredient, etc.)
│   └── SampleData.kt          # Przykładowe dane i repozytorium
├── ui/
│   ├── components/
│   │   └── BottomNavBar.kt    # Komponent dolnej nawigacji
│   ├── navigation/
│   │   └── Navigation.kt      # Definicje ekranów i nawigacji
│   ├── screens/
│   │   ├── OnboardingScreen.kt      # Ekran powitalny z karuzelą
│   │   ├── HomeScreen.kt            # Strona główna
│   │   ├── RecipesScreen.kt         # Lista przepisów + wyszukiwanie + filtry
│   │   ├── RecipeDetailScreen.kt    # Szczegóły przepisu
│   │   ├── ShoppingListScreen.kt    # Lista zakupów
│   │   └── SettingsScreen.kt        # Ustawienia i profil
│   └── theme/
│       ├── Color.kt           # Paleta kolorów
│       ├── Typography.kt      # Definicje typografii
│       └── Theme.kt           # Temat główny aplikacji
└── App.kt                     # Główna aplikacja z nawigacją
```

# Mini-Recipe App - Architecture

Mini-Recipe App is now organized as a single KMP module with a clear layered structure:

- `domain` contains pure models, repository contracts, and use cases.
- `data` contains in-memory repository implementations and the shared sample data source.
- `presentation` contains navigation, app state, screens, and reusable UI components.
- `ui/theme` keeps the shared Material 3 theme.

## Current Structure

```text
composeApp/src/commonMain/kotlin/org/example/project/
├── App.kt
├── data/
│   ├── datasource/StaticRecipeDataSource.kt
│   └── repository/
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
├── presentation/
│   ├── app/
│   ├── components/
│   │   ├── molecules/
│   │   └── organisms/
│   ├── navigation/
│   └── screens/
└── ui/theme/
  ├── Color.kt
  ├── Typography.kt
  └── Theme.kt
```

## Layer Responsibilities

### Domain

Pure Kotlin only. This layer defines the business language of the app:

- `Recipe`, `Ingredient`, `ShoppingListItem`, `UserProfile`, `OnboardingSlide`, `RecipeFilter`
- repository contracts such as `RecipeRepository`, `ShoppingListRepository`, `UserProfileRepository`
- use cases such as recipe filtering, onboarding content retrieval, and shopping list mutations

### Data

Shared data implementations. Currently this is in-memory and sample-data driven, which keeps the app fully runnable without external services.

- `StaticRecipeDataSource` holds the sample content
- `InMemoryRecipeRepository` exposes recipes, categories, and onboarding slides
- `InMemoryShoppingListRepository` manages the shopping list in memory
- `InMemoryUserProfileRepository` manages the profile in memory

### Presentation

Compose UI and screen orchestration.

- `presentation.app.MiniRecipeApp` is the shell of the application
- `MiniRecipeAppState` owns the top-level state and coordinates use cases
- `presentation.navigation` contains screen and bottom-tab routing enums
- `presentation.components` follows Atomic Design conventions for reusable UI pieces
- `presentation.screens` contains the actual feature screens

## Atomic Design Mapping

- **Molecules**: `HomeActionCard`, `RecipeCard`, `SearchField`, `RecipeFiltersPanel`
- **Organisms**: `RecipeAppBottomNavBar`
- **Screens**: onboarding, home, recipes, recipe detail, shopping list, settings

## Validation Status

The refactor currently compiles successfully in the shared layer and Android Kotlin target. The Android Gradle resource task can still hit a Windows file-lock on `R.jar`, so the most reliable validation commands are:

```powershell
.
gradlew composeApp:compileCommonMainKotlinMetadata
.
gradlew composeApp:compileDebugKotlinAndroid -x processDebugResources
```

These two commands are the fastest way to verify changes in the new structure.

---

## 🎨 Komponenty Aplikacji

### 1. **Ekran Powitalny (Onboarding)**

- **Lokalizacja**: [ui/screens/OnboardingScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/OnboardingScreen.kt)
- **Funkcjonalność**:
  - 3 slajdy z ikonami, tytułami i opisami
  - Wskaźniki postępu
  - Przyciski nawigacji (Dalej, Wstecz, Pomiń, Rozpocznij)
  - Automatyczne przejście do ekranu Home

**Slajdy**:

1. 🍳 "Witaj w Mini-Recipe App!" - Przegląd funkcji
2. 🔍 "Wyszukuj i filtruj" - Filtrowanie przepisów
3. 🛒 "Zarządzaj listą zakupów" - Synkronizacja z przepisami

---

### 2. **Strona Główna (Home)**

- **Lokalizacja**: [ui/screens/HomeScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/HomeScreen.kt)
- **Funkcjonalność**:
  - Powitanie użytkownika
  - 4 karty szybkiego dostępu
  - Sekcja "Ostatnio przeglądane"

**Karty szybkiego dostępu**:

- 🍽️ Przeglądaj przepisy
- 🔍 Szukaj i filtruj
- 🛒 Zarządzaj listą zakupów
- 👤 Edytuj profil

---

### 3. **Lista Przepisów (Recipes)**

- **Lokalizacja**: [ui/screens/RecipesScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/RecipesScreen.kt)
- **Funkcjonalność**:
  - Lista przepisów w postaci kafelków
  - Wyszukiwanie po nazwie
  - Filtrowanie zaawansowane:
    - ⏱️ Czas przygotowania (slider 5-180 minut)
    - 🍴 Typ dania (śniadanie, obiad, kolacja, deser)
  - Wyświetlanie czasu, liczby porcji, ratingu

**Kafelki przepisów**:

```
┌─────────────────────────────┐
│  Zdjęcie dania (emoji)      │
│  Nazwa przepisu             │
│  ⏱️ 20 min | 👥 2 porcje | ⭐ 4.8 │
└─────────────────────────────┘
```

---

### 4. **Szczegóły Przepisu (Recipe Detail)**

- **Lokalizacja**: [ui/screens/RecipeDetailScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/RecipeDetailScreen.kt)
- **Funkcjonalność**:
  - Duże zdjęcie dania
  - Informacje podstawowe (czas, porcje, trudność)
  - Lista składników z ilościami
  - Instrukcje krok po kroku (numerowane)
  - Przycisk "Dodaj składniki do listy zakupów"

**Instrukcje**:

```
┌───┬─────────────────────────┐
│ 1 │ Mąkę zmieszaj z        │
│   │ proszkiem do pieczenia  │
└───┴─────────────────────────┘
```

---

### 5. **Lista Zakupów (Shopping List)**

- **Lokalizacja**: [ui/screens/ShoppingListScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/ShoppingListScreen.kt)
- **Funkcjonalność**:
  - Checkboxy do zaznaczania zakupionych produktów
  - Wyświetlanie ilości i jednostek
  - Dodawanie pozycji ręczne
  - Usuwanie pozycji
  - Licznik pozycji
  - Dialog do dodawania nowych pozycji

**Elementy listy**:

```
☑ Mleko                          1 l      ✕
☐ Jaja                          6 szt.    ✕
```

---

### 6. **Ustawienia i Profil (Settings)**

- **Lokalizacja**: [ui/screens/SettingsScreen.kt](../../src/commonMain/kotlin/org/example/project/ui/screens/SettingsScreen.kt)
- **Funkcjonalność**:

**Sekcja Profilu**:

- Avatar (placeholder)
- Imię użytkownika
- Email
- Przycisk edycji profilu

**Sekcja Wsparcia**:

- 📧 Przycisk "Zgłoś błąd"
- Dialog do wpisania opisu błędu

**Sekcja O Aplikacji**:

- Nazwa aplikacji
- Wersja (1.0.0)
- Autor
- Opis aplikacji

---

## 🧭 Dolna Nawigacja

- **Lokalizacja**: [ui/components/BottomNavBar.kt](../../src/commonMain/kotlin/org/example/project/ui/components/BottomNavBar.kt)
- **4 Zakładki**:
  1. 🏠 **Home** - Strona główna / Onboarding
  2. 🍽️ **Przepisy** - Lista i szczegóły przepisów
  3. 🛒 **Lista zakupów** - Zarządzanie listą
  4. ⚙️ **Ustawienia** - Profil i ustawienia

**Cechy**:

- Wyraźne podświetlenie wybranej zakładki
- Ikony zmieniane w zależności od stanu
- Zawsze widoczna na dole ekranu

---

## 🎨 Temat Wizualny

### Paleta Kolorów

- **Główny (Zielony)**: `#2E7D32`
- **Drugoplanowy (Pomarańczowy)**: `#D97706`
- **Tekst (Ciemnoszary)**: `#1F2937`
- **Tło**: `#FAFAFA` (prawie biel)

### Typografia

- **Headliny**: Bold, 20-28 sp
- **Tekst główny**: Regular, 14-16 sp
- **Etykiety**: SemiBold, 12-14 sp
- Font: Sans Serif (domyślny system)

### Elementy UI

- Zaokrąglone kąty: 8-16 dp
- Margin standardowy: 12-16 dp
- Padding standardowy: 8-12 dp

---

## 📊 Modele Danych

### Recipe (Przepis)

```kotlin
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val prepTime: Int,           // w minutach
    val servings: Int,
    val category: String,        // śniadanie, obiad, kolacja, deser
    val difficulty: String,      // łatwy, średni, trudny
    val ingredients: List<Ingredient>,
    val instructions: List<String>,
    val rating: Float
)
```

### Ingredient (Składnik)

```kotlin
data class Ingredient(
    val id: String,
    val name: String,
    val quantity: Double,
    val unit: String            // szt., ml, g, szklanka, itp.
)
```

### ShoppingListItem (Pozycja listy)

```kotlin
data class ShoppingListItem(
    val id: String,
    val ingredientName: String,
    val quantity: Double,
    val unit: String,
    val isChecked: Boolean,
    val recipeId: String?
)
```

### UserProfile (Profil użytkownika)

```kotlin
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String
)
```

---

## 📦 Przykładowe Dane

Aplikacja zawiera 5 przykładowych przepisów:

1. **Placki bananowe** - 20 min, 2 porcje, łatwy
2. **Sałatka Cezar** - 15 min, 2 porcje, łatwy
3. **Piernik z orzechami** - 45 min, 8 porcji, średni
4. **Pasta Carbonara** - 25 min, 2 porcje, średni
5. **Smoothie owocowe** - 10 min, 1 porcja, łatwy

Wszystkie przepisy zawierają:

- Pełne składniki z ilościami
- Instrukcje krok po kroku
- Zdjęcia (ikony emoji)
- Rating

---

## 🔄 Przepływ Nawigacji

```
Onboarding (3 slajdy)
    ↓
    └─→ Home (Strona główna)
            ├─→ Recipes (Lista przepisów)
            │       ↓
            │       └─→ Recipe Detail (Szczegóły)
            │               ↓
            │               └─→ Shopping List (Dodanie składników)
            ├─→ Shopping List (Zarządzanie listą)
            └─→ Settings (Profil i ustawienia)
```

---

## 🚀 Uruchamianie Projektu

### Wymagania

- Kotlin 1.9+
- Gradle 8.1+
- Java 11+

### Budowanie

```bash
./gradlew build
```

### Uruchamianie na Android

```bash
./gradlew composeApp:installDebug
```

### Uruchamianie na iOS

```bash
./gradlew iosApp:embedAndSignAppleFrameworkForXcode
```

---

## 💡 Cechy Aplikacji

✅ **Minimalistyczny design** - Czysty, nowoczesny interfejs  
✅ **Jasny motyw** - Lekkie, przyjazne dla oczu kolory  
✅ **Responsywny** - Działa na wszystkich rozmiarach ekranu  
✅ **Multiplatformowy** - Kotlin Multiplatform (Android, iOS)  
✅ **Czytelny** - Duże zdjęcia i wyraźne karty  
✅ **Spójna typografia** - Harmonijne skale tekstu  
✅ **Intuicyjna nawigacja** - Czytelne ikony i przesunięcia

---

## 🔮 Możliwe Rozszerzenia

- 💾 Baza danych (Room, SQLite)
- ☁️ Synchronizacja w chmurze
- ❤️ Ulubionych przepisów
- 📸 Zdjęcia z aparatu
- 🌙 Ciemny motyw
- 🌍 Wielojęzyczność
- 📊 Historia wyszukiwań
- 🔐 Uwierzytelnianie użytkownika
- 📧 Synchronizacja z email

---

## 📝 Notatki Twórcy

- Wszystkie ekrany są w pełni funkcjonalne
- Dane są przechowywane w pamięci (state)
- Interfejs jest responsywny i przystosowany do różnych rozmiarów
- Ikony używają emoji dla prostoty i uniwersalności
- Aplikacja obsługuje zarówno platformę Android, jak i iOS

---

**Wersja**: 1.0.0  
**Autor**: Your Company  
**Data**: 2026
