# Mini-Recipe App - Instrukcja Instalacji i Uruchamiania

## 🎯 Cel Projektu

Mini-Recipe App to kompleksowa aplikacja mobilna napisana w **Kotlin Multiplatform Compose**, która pozwala na:

- Przeglądanie i wyszukiwanie przepisów
- Filtrowanie dań po czasie i typie
- Zarządzanie listą zakupów
- Edytowanie profilu użytkownika

Aplikacja działa na **Android** i **iOS**, dzieląc wspólny kod za pośrednictwem Kotlin Multiplatform.

---

## 📋 Wymagania Systemowe

### Obowiązkowe

- **JDK**: Java 11 lub nowsze
- **Kotlin**: 1.9.x lub nowsze
- **Gradle**: 8.1 lub nowsze
- **Compose Multiplatform**: Najnowsza stabilna wersja

### Dla Androida

- **Android SDK**: API level 24+ (Android 7.0+)
- **Android Gradle Plugin**: 8.x+

### Dla iOS

- **Xcode**: 14.0 lub nowsze
- **macOS**: 12.0 lub nowsze
- **iOS deployment target**: 14.0+

---

## 🚀 Instalacja

### 1. Klonowanie Repozytorium

```bash
git clone https://github.com/yourusername/mini-recipe-app.git
cd mini-recipe-app/KotlinProject
```

### 2. Konfiguracja Gradle

Projekt używa Gradle Wrapper, więc nie musisz nic instalować:

```bash
# Windows
.\gradlew.bat --version

# macOS/Linux
./gradlew --version
```

### 3. Aktualizacja Zależności (Opcjonalnie)

```bash
./gradlew dependencies
```

---

## 🏗️ Budowanie Projektu

### Build na wszystkie platformy

```bash
./gradlew build
```

### Build na Androida

```bash
./gradlew composeApp:assembleDebug    # Debug APK
./gradlew composeApp:assembleRelease  # Release APK
```

### Build na iOS

```bash
./gradlew iosApp:embedAndSignAppleFrameworkForXcode
```

---

## 🎮 Uruchamianie Aplikacji

### Android - Emulator

```bash
# Lista dostępnych emulatorów
emulator -list-avds

# Uruchomienie emulatora
emulator -avd <avd_name>

# Instalacja i uruchamianie na emulatorze
./gradlew composeApp:installDebug
./gradlew composeApp:run
```

### Android - Urządzenie Fizyczne

```bash
# Upewnij się, że urządzenie jest podłączone i ma włączony USB Debugging
adb devices

# Instalacja
./gradlew composeApp:installDebug

# Uruchomienie
adb shell am start -n org.example.project/.MainActivity
```

### iOS - Symulator

```bash
# Otwórz projekt w Xcode
open iosApp/iosApp.xcodeproj

# Lub uruchom z linii komend
xcodebuild -scheme iosApp -configuration Debug -derivedDataPath build
```

### iOS - Urządzenie Fizyczne

Będziesz potrzebować:

1. Apple Developer Account
2. Podpisany certyfikat
3. Profil provisioning

```bash
# Wygeneruj framework
./gradlew iosApp:embedAndSignAppleFrameworkForXcode

# Otwórz w Xcode i podpisz
open iosApp/iosApp.xcodeproj
```

---

## 📁 Struktura Projektu

```
KotlinProject/
├── composeApp/
│   ├── src/
│   │   ├── commonMain/              # Wspólny kod (KMP)
│   │   │   └── kotlin/org/example/project/
│   │   │       ├── data/            # Modele i dane
│   │   │       ├── ui/              # Ekrany i komponenty
│   │   │       └── App.kt           # Główny plik
│   │   ├── androidMain/             # Kod specyficzny dla Android
│   │   └── iosMain/                 # Kod specyficzny dla iOS
│   ├── build.gradle.kts             # Konfiguracja Gradle
│   └── ...
├── iosApp/                          # Projekt iOS
├── gradle/                          # Gradle wrapper
├── build.gradle.kts                 # Konfiguracja główna
├── settings.gradle.kts              # Ustawienia projektu
└── gradle.properties                # Właściwości Gradle
```

---

## 🔧 Konfiguracja IDE

### Android Studio

1. Otwórz projekt: `File → Open → KotlinProject`
2. Czekaj na synchronizację Gradle
3. Wybierz Android Gradle Plugin: `Gradle 8.x`
4. Uruchom: `Run → Run 'composeApp'`

### IntelliJ IDEA

1. Otwórz projekt
2. Czekaj na indeksowanie
3. Zainstaluj plugin Kotlin (powinien być automatycznie)
4. Uruchom build: `Build → Build Project`

### VS Code (Visual Studio Code)

1. Zainstaluj rozszerzenia:
   - Kotlin Language Server
   - Gradle for Java

2. Otwórz terminal i wykonaj:

```bash
./gradlew build
```

---

## 🧪 Testowanie

### Uruchomienie Testów Unit

```bash
./gradlew commonTest
```

### Uruchomienie Wszystkich Testów

```bash
./gradlew test
```

---

## 🎨 Dostosowywanie Projektu

### Zmiana Palety Kolorów

Edytuj plik: `src/commonMain/kotlin/org/example/project/ui/theme/Color.kt`

```kotlin
val RecipePrimary = Color(0xFF2E7D32)  // Zmień na inny kolor
```

### Zmiana Typografii

Edytuj plik: `src/commonMain/kotlin/org/example/project/ui/theme/Typography.kt`

### Dodanie Nowych Ekranów

1. Utwórz plik w `ui/screens/`
2. Dodaj ekran do `Navigation.kt`
3. Zaktualizuj `App.kt`

---

## 📦 Publikowanie

### Android - Google Play Store

1. Podpisz APK:

```bash
./gradlew composeApp:bundleRelease
```

2. Zaloguj się do Google Play Console
3. Utwórz nową aplikację
4. Prześlij bundle (.aab)
5. Postępuj zgodnie z instrukcjami

### iOS - App Store

1. Wygeneruj release build
2. Zaloguj się do App Store Connect
3. Utwórz nową aplikację
4. Prześlij build
5. Poczekaj na review

---

## 🐛 Debugowanie

### Logowanie

```kotlin
import android.util.Log

// W kodzie
Log.d("TAG", "Wiadomość debugowania")
```

### Android Studio Debugger

1. Ustaw breakpoint klikając na numer linii
2. Uruchom w trybie debugowania: `Debug → Debug 'composeApp'`
3. Aplikacja zatrzyma się na breakpoint

### Logcat

```bash
# Wyświetl logi
adb logcat

# Filtruj po tagu
adb logcat | grep TAG
```

---

## 📚 Zasoby

- [Kotlin Docs](https://kotlinlang.org/docs/)
- [Jetpack Compose Docs](https://developer.android.com/develop/ui/compose)
- [Kotlin Multiplatform Docs](https://kotlinlang.org/docs/multiplatform.html)
- [Android Developers](https://developer.android.com/)

---

## ⚠️ Rozwiązywanie Problemów

### Problem: Gradle synchronization failed

```bash
# Wyczyść cache
./gradlew clean

# Pełny rebuild
./gradlew build --refresh-dependencies
```

### Problem: Kompilacja się nie powiedzie

```bash
# Upewnij się, że masz prawidłową wersję JDK
java -version

# Sprawdź wersję Gradle
./gradlew --version
```

### Problem: Symulator Android się nie uruchamia

```bash
# Zabij poprzedni proces
pkill -f emulator

# Uruchom ponownie
emulator -avd <avd_name> -no-snapshot-load
```

---

## 📝 Notatki Dla Programistów

- Projekt używa **Kotlin Multiplatform** (KMP) dla współdzielonego kodu
- Interfejs użytkownika jest zbudowany z **Jetpack Compose**
- Architektura opiera się na wzorze **MVVM**
- Dane są przechowywane w **pamięci** (state)
- Projekt jest gotowy do dodania bazy danych i API

---

## 🔐 Bezpieczeństwo

Aktualnie aplikacja:

- ✅ Nie przechowuje poufnych danych
- ✅ Nie wysyła danych do sieci
- ✅ Nie wymaga uprawnień do niebezpiecznych funkcji
- ⚠️ Powinna zostać rozszerzona o szyfrowanie danych jeśli będzie przechowywana wrażliwa informacja

---

## 📈 Dalsze Ulepszenia

- [ ] Dodanie bazy danych (Room)
- [ ] Synchronizacja z backendem
- [ ] Dodawanie własnych przepisów
- [ ] System recenzji
- [ ] Powiadomienia
- [ ] Ciemny motyw
- [ ] Wielojęzyczność
- [ ] Eksport listy zakupów

---

**Gotów do pracy! 🚀**

Jeśli masz pytania, sprawdź dokumentację lub otwórz issue w repozytorium.
