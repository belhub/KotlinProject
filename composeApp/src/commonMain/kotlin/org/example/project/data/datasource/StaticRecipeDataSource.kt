package org.example.project.data.datasource

import org.example.project.domain.model.Ingredient
import org.example.project.domain.model.OnboardingSlide
import org.example.project.domain.model.Recipe
import org.example.project.domain.model.ShoppingListItem

object StaticRecipeDataSource {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "1",
            title = "Placki bananowe",
            description = "Puchate placki z bananem na śniadanie",
            imageUrl = "🥞",
            prepTime = 20,
            servings = 2,
            category = "śniadanie",
            difficulty = "łatwy",
            ingredients = listOf(
                Ingredient("1", "Banany", 2.0, "szt."),
                Ingredient("2", "Mąka pszenna", 200.0, "g"),
                Ingredient("3", "Jaja", 2.0, "szt."),
                Ingredient("4", "Mleko", 150.0, "ml"),
                Ingredient("5", "Proszek do pieczenia", 1.0, "op."),
                Ingredient("6", "Cukier", 2.0, "łyżki")
            ),
            instructions = listOf(
                "Mąkę zmieszaj z proszkiem do pieczenia",
                "Banany rozgnieć widelcem",
                "Ubij jaja z cukrem",
                "Połącz wszystkie składniki",
                "Smaż na niezbyt gorącej patelni",
                "Aż do zrumienienia"
            ),
            rating = 4.8f
        ),
        Recipe(
            id = "2",
            title = "Sałatka Cezar",
            description = "Klasyczna sałatka z kruszonką i sardelą",
            imageUrl = "🥗",
            prepTime = 15,
            servings = 2,
            category = "obiad",
            difficulty = "łatwy",
            ingredients = listOf(
                Ingredient("1", "Sałata rzymska", 200.0, "g"),
                Ingredient("2", "Sardele", 100.0, "g"),
                Ingredient("3", "Parmezan", 50.0, "g"),
                Ingredient("4", "Chleb", 100.0, "g"),
                Ingredient("5", "Majonez", 100.0, "ml"),
                Ingredient("6", "Czosnek", 1.0, "szt.")
            ),
            instructions = listOf(
                "Chleb pokrój w kostki i przysmażej z czosnkiem",
                "Sałatę umyj i pokrój",
                "Przygotuj sos z majonezu i anchois",
                "Połącz sałatę z sosem",
                "Dodaj croutony i parmezan",
                "Podawaj od razu"
            ),
            rating = 4.5f
        ),
        Recipe(
            id = "3",
            title = "Piernik z orzechami",
            description = "Przepyszny piernik z orzechami włoskimi",
            imageUrl = "🍰",
            prepTime = 45,
            servings = 8,
            category = "deser",
            difficulty = "średni",
            ingredients = listOf(
                Ingredient("1", "Mąka", 250.0, "g"),
                Ingredient("2", "Orzeszki włoskie", 150.0, "g"),
                Ingredient("3", "Jaja", 3.0, "szt."),
                Ingredient("4", "Masło", 150.0, "g"),
                Ingredient("5", "Cukier", 200.0, "g"),
                Ingredient("6", "Przyprawy do piernika", 2.0, "łyżeczki")
            ),
            instructions = listOf(
                "Roztop masło i wymieszaj z cukrem",
                "Dodaj jaja jedno po drugim",
                "Zmiel orzechy",
                "Połącz mąkę, orzechy i przyprawy",
                "Wymieszaj wszystkie składniki",
                "Przełóż do formy",
                "Piecz 40 minut w 180°C"
            ),
            rating = 4.9f
        ),
        Recipe(
            id = "4",
            title = "Pasta Carbonara",
            description = "Włoska pasta z bekonem i żółtkami",
            imageUrl = "🍝",
            prepTime = 25,
            servings = 2,
            category = "obiad",
            difficulty = "średni",
            ingredients = listOf(
                Ingredient("1", "Makaron spaghetti", 400.0, "g"),
                Ingredient("2", "Boczek", 150.0, "g"),
                Ingredient("3", "Żółtka", 3.0, "szt."),
                Ingredient("4", "Parmezan", 100.0, "g"),
                Ingredient("5", "Czarny pieprz", 1.0, "łyżeczka"),
                Ingredient("6", "Sól", 1.0, "szczypta")
            ),
            instructions = listOf(
                "Gotuj makaron w słonej wodzie",
                "Pokrój boczek w paski i smaż",
                "Ubij żółtka z parmezanem",
                "Odcedź makaron (zachowaj 1 szklankę wywarki)",
                "Szybko wymieszaj makaron z boczkiem",
                "Dodaj żółtka i wywarku",
                "Przypraw pieprzem i podawaj"
            ),
            rating = 4.7f
        ),
        Recipe(
            id = "5",
            title = "Smoothie owocowe",
            description = "Zdrowe smoothie z owocami i jogurtem",
            imageUrl = "🥤",
            prepTime = 10,
            servings = 1,
            category = "śniadanie",
            difficulty = "łatwy",
            ingredients = listOf(
                Ingredient("1", "Banany", 1.0, "szt."),
                Ingredient("2", "Truskawki", 150.0, "g"),
                Ingredient("3", "Jogurt naturalny", 200.0, "ml"),
                Ingredient("4", "Miód", 1.0, "łyżka"),
                Ingredient("5", "Lód", 100.0, "g")
            ),
            instructions = listOf(
                "Banany i truskawki pokrój",
                "Wrzuć do blendera",
                "Dodaj jogurt i miód",
                "Dodaj lód",
                "Miksuj aż do gładkości",
                "Podawaj natychmiast"
            ),
            rating = 4.6f
        ),
      Recipe(
          id = "10",
          title = "Owsianka z malinami",
          description = "Kremowa owsianka z malinami i miodem",
          imageUrl = "https://images.unsplash.com/photo-1506084868230-bb9d95c24759?auto=format&fit=crop&w=1200&q=80",
          prepTime = 12,
          servings = 1,
          category = "śniadanie",
          difficulty = "łatwy",
          ingredients = listOf(
              Ingredient("1", "Płatki owsiane", 60.0, "g"),
              Ingredient("2", "Mleko", 200.0, "ml"),
              Ingredient("3", "Maliny", 80.0, "g"),
              Ingredient("4", "Miód", 1.0, "łyżka"),
              Ingredient("5", "Cynamon", 0.5, "łyżeczki")
          ),
          instructions = listOf(
              "Podgrzej mleko w rondelku.",
              "Dodaj płatki owsiane i gotuj 5 minut.",
              "Dodaj miód i cynamon.",
              "Przełóż do miski i udekoruj malinami."
          ),
          rating = 4.7f
      ),
      Recipe(
          id = "11",
          title = "Kurczak w sosie teriyaki",
          description = "Soczysty kurczak w aromatycznym sosie teriyaki",
          imageUrl = "https://images.unsplash.com/photo-1604908177522-4326a5b5a4d1?auto=format&fit=crop&w=1200&q=80",
          prepTime = 25,
          servings = 2,
          category = "obiad",
          difficulty = "średni",
          ingredients = listOf(
              Ingredient("1", "Pierś z kurczaka", 300.0, "g"),
              Ingredient("2", "Sos teriyaki", 120.0, "ml"),
              Ingredient("3", "Ryż", 150.0, "g"),
              Ingredient("4", "Czosnek", 1.0, "ząbek"),
              Ingredient("5", "Olej sezamowy", 1.0, "łyżka")
          ),
          instructions = listOf(
              "Pokrój kurczaka w paski.",
              "Podsmaż na oleju sezamowym.",
              "Dodaj czosnek i sos teriyaki.",
              "Gotuj aż sos zgęstnieje.",
              "Podawaj z ugotowanym ryżem."
          ),
          rating = 4.8f
      ),
      Recipe(
          id = "12",
          title = "Brownie czekoladowe",
          description = "Wilgotne brownie z gorzkiej czekolady",
          imageUrl = "https://images.unsplash.com/photo-1606312619070-d48b4c2c3f55?auto=format&fit=crop&w=1200&q=80",
          prepTime = 35,
          servings = 6,
          category = "deser",
          difficulty = "łatwy",
          ingredients = listOf(
              Ingredient("1", "Czekolada gorzka", 200.0, "g"),
              Ingredient("2", "Masło", 120.0, "g"),
              Ingredient("3", "Cukier", 150.0, "g"),
              Ingredient("4", "Jaja", 3.0, "szt."),
              Ingredient("5", "Mąka", 80.0, "g")
          ),
          instructions = listOf(
              "Rozpuść masło z czekoladą.",
              "Dodaj cukier i wymieszaj.",
              "Dodaj jaja i mąkę.",
              "Wylej masę do formy.",
              "Piecz 25 minut w 180°C."
          ),
          rating = 4.9f
      ),
      Recipe(
          id = "13",
          title = "Zupa krem z dyni",
          description = "Aksamitna zupa dyniowa z imbirem",
          imageUrl = "https://images.unsplash.com/photo-1601050690597-df7e4cbd1d5b?auto=format&fit=crop&w=1200&q=80",
          prepTime = 30,
          servings = 4,
          category = "obiad",
          difficulty = "średni",
          ingredients = listOf(
              Ingredient("1", "Dynia", 600.0, "g"),
              Ingredient("2", "Bulion warzywny", 500.0, "ml"),
              Ingredient("3", "Imbir", 1.0, "łyżeczka"),
              Ingredient("4", "Śmietanka 30%", 50.0, "ml"),
              Ingredient("5", "Cebula", 1.0, "szt.")
          ),
          instructions = listOf(
              "Podsmaż cebulę i imbir.",
              "Dodaj pokrojoną dynię.",
              "Zalej bulionem i gotuj 20 minut.",
              "Zblenduj na gładki krem.",
              "Dodaj śmietankę i wymieszaj."
          ),
          rating = 4.6f
      ),
      Recipe(
          id = "14",
          title = "Kanapka z łososiem",
          description = "Świeża kanapka z wędzonym łososiem i koperkiem",
          imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=1200&q=80",
          prepTime = 10,
          servings = 1,
          category = "śniadanie",
          difficulty = "łatwy",
          ingredients = listOf(
              Ingredient("1", "Chleb pełnoziarnisty", 2.0, "kromki"),
              Ingredient("2", "Łosoś wędzony", 60.0, "g"),
              Ingredient("3", "Serek śmietankowy", 2.0, "łyżki"),
              Ingredient("4", "Koperek", 1.0, "łyżeczka"),
              Ingredient("5", "Cytryna", 1.0, "plaster")
          ),
          instructions = listOf(
              "Posmaruj pieczywo serkiem.",
              "Dodaj łososia.",
              "Posyp koperkiem.",
              "Skrop cytryną i podawaj."
          ),
          rating = 4.5f
      ),
      Recipe(
        id = "15",
        title = "Tosty francuskie",
        description = "Chrupiące tosty francuskie z owocami",
        imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=1200&q=80",
        prepTime = 15,
        servings = 2,
        category = "śniadanie",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Chleb tostowy", 4.0, "szt."),
            Ingredient("2", "Jaja", 2.0, "szt."),
            Ingredient("3", "Mleko", 80.0, "ml"),
            Ingredient("4", "Masło", 20.0, "g"),
            Ingredient("5", "Truskawki", 100.0, "g")
        ),
        instructions = listOf(
            "Wymieszaj jaja z mlekiem.",
            "Zanurz kromki chleba w mieszance.",
            "Smaż na maśle do zarumienienia.",
            "Podawaj z owocami."
        ),
        rating = 4.6f
    ),
    Recipe(
        id = "16",
        title = "Gulasz wołowy",
        description = "Aromatyczny gulasz z warzywami",
        imageUrl = "https://images.unsplash.com/photo-1604909053197-3e4d8b15a1a1?auto=format&fit=crop&w=1200&q=80",
        prepTime = 90,
        servings = 4,
        category = "obiad",
        difficulty = "średni",
        ingredients = listOf(
            Ingredient("1", "Wołowina", 500.0, "g"),
            Ingredient("2", "Cebula", 1.0, "szt."),
            Ingredient("3", "Marchew", 2.0, "szt."),
            Ingredient("4", "Bulion", 400.0, "ml"),
            Ingredient("5", "Papryka słodka", 1.0, "łyżeczka")
        ),
        instructions = listOf(
            "Podsmaż wołowinę.",
            "Dodaj cebulę i marchew.",
            "Zalej bulionem.",
            "Gotuj 70 minut na małym ogniu."
        ),
        rating = 4.8f
    ),
    Recipe(
        id = "17",
        title = "Tiramisu",
        description = "Klasyczne włoskie tiramisu",
        imageUrl = "https://images.unsplash.com/photo-1608219959302-6d4d8f8e1b5b?auto=format&fit=crop&w=1200&q=80",
        prepTime = 30,
        servings = 6,
        category = "deser",
        difficulty = "średni",
        ingredients = listOf(
            Ingredient("1", "Mascarpone", 250.0, "g"),
            Ingredient("2", "Jaja", 3.0, "szt."),
            Ingredient("3", "Cukier", 60.0, "g"),
            Ingredient("4", "Biszkopty", 200.0, "g"),
            Ingredient("5", "Kawa", 150.0, "ml")
        ),
        instructions = listOf(
            "Ubij żółtka z cukrem.",
            "Dodaj mascarpone.",
            "Namocz biszkopty w kawie.",
            "Układaj warstwami i schłódź."
        ),
        rating = 4.9f
    ),
    Recipe(
        id = "18",
        title = "Krem pomidorowy",
        description = "Delikatna zupa krem z pomidorów",
        imageUrl = "https://images.unsplash.com/photo-1551183053-8b9d0c7e5f9b?auto=format&fit=crop&w=1200&q=80",
        prepTime = 25,
        servings = 3,
        category = "obiad",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Pomidory", 600.0, "g"),
            Ingredient("2", "Cebula", 1.0, "szt."),
            Ingredient("3", "Bulion warzywny", 300.0, "ml"),
            Ingredient("4", "Bazylia", 1.0, "garść"),
            Ingredient("5", "Śmietanka", 40.0, "ml")
        ),
        instructions = listOf(
            "Podsmaż cebulę.",
            "Dodaj pomidory i bulion.",
            "Gotuj 15 minut.",
            "Zblenduj i dodaj śmietankę."
        ),
        rating = 4.5f
    ),
    Recipe(
        id = "19",
        title = "Wrap z kurczakiem",
        description = "Szybki wrap z kurczakiem i warzywami",
        imageUrl = "https://images.unsplash.com/photo-1601050690597-df7e4cbd1d5b?auto=format&fit=crop&w=1200&q=80",
        prepTime = 20,
        servings = 2,
        category = "obiad",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Tortilla", 2.0, "szt."),
            Ingredient("2", "Kurczak", 200.0, "g"),
            Ingredient("3", "Sałata", 50.0, "g"),
            Ingredient("4", "Pomidor", 1.0, "szt."),
            Ingredient("5", "Sos jogurtowy", 2.0, "łyżki")
        ),
        instructions = listOf(
            "Podsmaż kurczaka.",
            "Pokrój warzywa.",
            "Nałóż składniki na tortillę.",
            "Zwiń i podawaj."
        ),
        rating = 4.4f
    ),
    Recipe(
        id = "20",
        title = "Sernik na zimno",
        description = "Lekki sernik z owocami",
        imageUrl = "https://images.unsplash.com/photo-1505253716362-afaea1d3d1af?auto=format&fit=crop&w=1200&q=80",
        prepTime = 40,
        servings = 8,
        category = "deser",
        difficulty = "średni",
        ingredients = listOf(
            Ingredient("1", "Twaróg mielony", 500.0, "g"),
            Ingredient("2", "Śmietanka 30%", 200.0, "ml"),
            Ingredient("3", "Cukier", 100.0, "g"),
            Ingredient("4", "Galaretka", 1.0, "op."),
            Ingredient("5", "Herbatniki", 150.0, "g")
        ),
        instructions = listOf(
            "Rozgnieć herbatniki.",
            "Wymieszaj twaróg ze śmietanką.",
            "Dodaj rozpuszczoną galaretkę.",
            "Wylej masę na spód i schłódź."
        ),
        rating = 4.8f
    ),
    Recipe(
        id = "21",
        title = "Sałatka grecka",
        description = "Świeża sałatka z fetą i oliwkami",
        imageUrl = "https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=1200&q=80",
        prepTime = 12,
        servings = 2,
        category = "kolacja",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Pomidor", 2.0, "szt."),
            Ingredient("2", "Ogórek", 1.0, "szt."),
            Ingredient("3", "Feta", 100.0, "g"),
            Ingredient("4", "Oliwki", 50.0, "g"),
            Ingredient("5", "Oliwa", 1.0, "łyżka")
        ),
        instructions = listOf(
            "Pokrój warzywa.",
            "Dodaj fetę i oliwki.",
            "Polej oliwą i wymieszaj."
        ),
        rating = 4.6f
    ),
    Recipe(
        id = "22",
        title = "Pizza Margherita",
        description = "Klasyczna pizza z mozzarellą i bazylią",
        imageUrl = "https://images.unsplash.com/photo-1548365328-8b849e1c7a8c?auto=format&fit=crop&w=1200&q=80",
        prepTime = 30,
        servings = 2,
        category = "obiad",
        difficulty = "średni",
        ingredients = listOf(
            Ingredient("1", "Ciasto na pizzę", 1.0, "szt."),
            Ingredient("2", "Sos pomidorowy", 150.0, "ml"),
            Ingredient("3", "Mozzarella", 150.0, "g"),
            Ingredient("4", "Bazylia", 1.0, "garść")
        ),
        instructions = listOf(
            "Rozwałkuj ciasto.",
            "Posmaruj sosem.",
            "Dodaj mozzarellę.",
            "Piecz 12 minut w 250°C."
        ),
        rating = 4.7f
    ),
    Recipe(
        id = "23",
        title = "Smoothie zielone",
        description = "Orzeźwiające smoothie z jarmużem i jabłkiem",
        imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=1200&q=80",
        prepTime = 8,
        servings = 1,
        category = "śniadanie",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Jarmuż", 40.0, "g"),
            Ingredient("2", "Jabłko", 1.0, "szt."),
            Ingredient("3", "Banany", 1.0, "szt."),
            Ingredient("4", "Woda", 150.0, "ml")
        ),
        instructions = listOf(
            "Wrzuć składniki do blendera.",
            "Miksuj do uzyskania gładkiej konsystencji."
        ),
        rating = 4.3f
    ),
    Recipe(
        id = "24",
        title = "Makaron pesto",
        description = "Szybki makaron z pesto bazyliowym",
        imageUrl = "https://images.unsplash.com/photo-1525755662778-989d0524087e?auto=format&fit=crop&w=1200&q=80",
        prepTime = 15,
        servings = 2,
        category = "obiad",
        difficulty = "łatwy",
        ingredients = listOf(
            Ingredient("1", "Makaron penne", 250.0, "g"),
            Ingredient("2", "Pesto bazyliowe", 3.0, "łyżki"),
            Ingredient("3", "Parmezan", 40.0, "g"),
            Ingredient("4", "Oliwa", 1.0, "łyżeczka")
        ),
        instructions = listOf(
            "Ugotuj makaron.",
            "Wymieszaj z pesto.",
            "Dodaj oliwę i parmezan."
        ),
        rating = 4.6f
    )

    )

    val categories: List<String> = listOf("śniadanie", "obiad", "kolacja", "deser")

    val onboardingSlides: List<OnboardingSlide> = listOf(
        OnboardingSlide(
            title = "Witaj w Mini-Recipe App!",
            description = "Odkryj tysiące przepisów i uciesz się gotowaniem",
            icon = "🍳",
            features = listOf(
                "Przeglądaj przepisy",
                "Wyszukuj i filtruj dania",
                "Zarządzaj listą zakupów",
                "Edytuj swój profil"
            )
        ),
        OnboardingSlide(
            title = "Wyszukuj i filtruj",
            description = "Łatwo znajdź przepisy dopasowane do Twoich potrzeb",
            icon = "🔍",
            features = listOf(
                "Szukaj po nazwie",
                "Filtruj po czasie przygotowania",
                "Wybierz typ dania",
                "Zobacz rating"
            )
        ),
        OnboardingSlide(
            title = "Zarządzaj listą zakupów",
            description = "Nigdy nie zapomnij o ważnym składniku",
            icon = "🛒",
            features = listOf(
                "Dodawaj składniki",
                "Oznaczaj odhaczone",
                "Usuwaj pozycje",
                "Synchronizuj z przepisami"
            )
        )
    )

    val shoppingListItems: List<ShoppingListItem> = listOf(
        ShoppingListItem("1", "Mleko", 1.0, "l", false),
        ShoppingListItem("2", "Jaja", 6.0, "szt.", true),
        ShoppingListItem("3", "Mąka", 1.0, "kg", false),
        ShoppingListItem("4", "Cukier", 0.5, "kg", false),
        ShoppingListItem("5", "Masło", 250.0, "g", true)
    )
}