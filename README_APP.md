# 🍳 Mini-Recipe App

## Nowoczesna aplikacja mobilna do zarządzania przepisami i listą zakupów

Napisana w **Kotlin Multiplatform** | Compose | Material Design 3

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Platforms](https://img.shields.io/badge/platforms-Android%20%7C%20iOS-green)
![License](https://img.shields.io/badge/license-MIT-orange)

---

## 🎯 Funkcjonalność

✨ **Ekran Powitalny** - Carousel z 3 slajdami edukacyjnymi  
🏠 **Strona Główna** - Szybki dostęp do głównych funkcji  
🍽️ **Przepisy** - Przeglądaj, wyszukuj i filtruj dania  
🔍 **Zaawansowane Filtrowanie** - Po czasie, typie i ratingu  
🛒 **Lista Zakupów** - Zarządzaj składnikami i zakupami  
👤 **Profil Użytkownika** - Edycja danych i ustawień  
📱 **Nawigacja Dolna** - Szybki dostęp do 4 głównych sekcji

---

## 🚀 Szybki Start

### Wymagania

- JDK 11+
- Kotlin 1.9+
- Gradle 8.1+

### Instalacja

```bash
# Klonowanie
git clone https://github.com/yourusername/mini-recipe-app.git
cd mini-recipe-app/KotlinProject

# Budowanie
./gradlew build

# Android
./gradlew composeApp:installDebug
./gradlew composeApp:run

# iOS
./gradlew iosApp:embedAndSignAppleFrameworkForXcode
open iosApp/iosApp.xcodeproj
```

---

## 📱 Ekrany Aplikacji

### 1. Onboarding (Ekran Powitalny)

- 3 informatywne slajdy
- Wskaźniki postępu
- Pełna nawigacja

### 2. Home (Strona Główna)

- Powitanie użytkownika
- 4 karty szybkiego dostępu
- Historia przeglądanych przepisów

### 3. Recipes (Lista Przepisów)

- Kafelki przepisów
- Pole wyszukiwania
- Filtry (czas, typ dania)
- 5 przykładowych przepisów

### 4. Recipe Detail (Szczegóły)

- Zdjęcie dania
- Informacje podstawowe
- Lista składników
- Instrukcje krok po kroku
- Przycisk do listy zakupów

### 5. Shopping List (Lista Zakupów)

- Pozycje z checkboxami
- Dodawanie ręczne
- Usuwanie pozycji
- Integracja z przepisami

### 6. Settings (Ustawienia)

- Edycja profilu
- Zgłaszanie błędów
- Informacje o aplikacji

---

## 🎨 Design System

### Kolory

- **Primary (Zielony)**: `#2E7D32`
- **Secondary (Pomarańczowy)**: `#D97706`
- **Background (Jasny)**: `#FAFAFA`
- **Surface (Biały)**: `#FFFFFF`

### Typografia

- **Headlines**: Bold 20-28sp
- **Body**: Regular 14-16sp
- **Labels**: SemiBold 12-14sp

### Komponenty

- Zaokrąglone karty (12-16dp)
- Padding standardowy (12-16dp)
- Responsive layout
- Material Design 3

---

## 📂 Struktura Projektu

```
composeApp/src/commonMain/kotlin/org/example/project/
├── data/
│   ├── Models.kt          ← Modele danych
│   └── SampleData.kt      ← Przykładowe dane
├── ui/
│   ├── components/
│   │   └── BottomNavBar.kt
│   ├── navigation/
│   │   └── Navigation.kt
│   ├── screens/
│   │   ├── OnboardingScreen.kt
│   │   ├── HomeScreen.kt
│   │   ├── RecipesScreen.kt
│   │   ├── RecipeDetailScreen.kt
│   │   ├── ShoppingListScreen.kt
│   │   └── SettingsScreen.kt
│   └── theme/
│       ├── Color.kt
│       ├── Typography.kt
│       └── Theme.kt
└── App.kt                 ← Główny plik aplikacji
```

---

## 🧬 Modele Danych

### Recipe (Przepis)

```kotlin
data class Recipe(
    val id: String,
    val title: String,
    val prepTime: Int,        // minuty
    val servings: Int,
    val category: String,     // śniadanie, obiad, kolacja, deser
    val ingredients: List<Ingredient>,
    val instructions: List<String>,
    val rating: Float
)
```

### ShoppingListItem (Pozycja Listy)

```kotlin
data class ShoppingListItem(
    val id: String,
    val ingredientName: String,
    val quantity: Double,
    val unit: String,         // szt., ml, g, itp.
    val isChecked: Boolean,
    val recipeId: String?
)
```

---

## 🔧 Technologia

- **Language**: Kotlin 1.9+
- **UI Framework**: Jetpack Compose
- **Multiplatform**: Kotlin Multiplatform (KMP)
- **Android**: API 24+ (Android 7.0+)
- **iOS**: iOS 14.0+
- **Architecture**: MVVM
- **Design**: Material Design 3

---

## 📊 Statystyki

| Metryka      | Wartość |
| ------------ | ------- |
| Ekranów      | 6       |
| Komponenty   | 20+     |
| Modeli       | 6       |
| Linii kodu   | ~2500+  |
| Przepisów    | 5       |
| Dokumentacji | 3 pliki |

---

## 📖 Dokumentacja

- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Architektura i struktura projektu
- **[USER_GUIDE.md](USER_GUIDE.md)** - Instrukcja użytkownika
- **[INSTALLATION.md](INSTALLATION.md)** - Instrukcja instalacji i uruchamiania
- **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** - Podsumowanie implementacji

---

## 🔮 Plany Przyszłościowe

- [ ] Backend API
- [ ] Baza danych (Room)
- [ ] Synchronizacja w chmurze
- [ ] Ulubionych przepisów
- [ ] Zdjęcia użytkownika
- [ ] Ciemny motyw
- [ ] Wielojęzyczność
- [ ] Powiadomienia push
- [ ] Eksport listy zakupów

---

## 🐛 Znane Problemy

Aktualnie aplikacja nie zawiera znanych błędów.

Jeśli znalazłeś bug, zgłoś go w aplikacji: **Ustawienia → Wsparcie → Zgłoś błąd**

---

## 📝 Licencja

Projekt jest dostępny na licencji MIT.

---

## 👤 Autor

**Your Company**  
Wersja: 1.0.0  
Data: 2026

---

## 🙏 Podziękowania

Dziękuję za korzystanie z Mini-Recipe App!

---

<div align="center">

**Stwórz, wyszukaj, gotuj! 🍳**

[📖 Pełna dokumentacja](ARCHITECTURE.md) • [🚀 Instalacja](INSTALLATION.md) • [👥 Profil](https://github.com/yourusername)

</div>
