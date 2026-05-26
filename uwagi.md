# DESIGN

## Pierwszy prompt tylko tekstowy

0. Ogólne:

- pierwszy prompt z błędami - poprawiał długo, żeby wgl aplikacja wystartowała
- slidery bez animacji, na przyciski działające
- taby zmieniają ikony po kliknięciu, czy to plus cyz minus? - nie trzymanie się treści prompta
- margin dolny jest za mały - taby zasłaniają dolną treść ekranu
- dużo błędów

* rozdzielenie projektu na kilka folderów - pełna struktura
* kilka dodatkowych plików md które opisują aplikację: architekture, jak zainstalować, readme, user guide, podsumowanie

- taby z zaogrąglonym tłem

1. Home:

- slidery bez animacji, nie da się wgl do nich wrócić - po przejściu sliderów przechodzimy do ekranu strony głównej gdzie:
  - pojawia się lista z nazwami ekranów sugerujące że są podpięte do zadań nawigacji jednak przyciski nic nie robią
  - dodatkowo jest lista ostatnio przeglądanych przepisów, co równiez nie działa ponieważ cały czas wyświetla - brak ostatnio przeglądanych przepisów
- górny margin jest za mały - nachodzi na górny pasek narzędzi systemu, reszta ekrnaów działa poprawnie

* animacja kliku - tylko po co - jak nie działa xD

2. Przepisy:

- wyszukiwarka ucina dół wpisywanych liter
- na kafelkach przepisów bnrakuje informacji czy to obiad czy kolacja czy deser czy śniadanie
- nie podoba mi się styl filtrów, spróbuje go przebudować, aby nie było paska

* ładne kafelki, spójne marhinesy

3. Lista zakupów:

- nic nie działa XD
  - nie działa usuwanie
  - nie działa zaznaczanie
  - nie działa odznaczanie
- nie można dodać nowego produktu do listy - brak formularza
- zaznaczone produkty nie przechodzą na dół listy, do zakładki zakupione

4. ustawienia:

- brak walidacji maila
- brak komunikatu że wysłano wiadomość zgłaszajacą błąd
- imie może być puste
- mail może być pusty

* ładna separacja
* spójne ui

## Poprawienie struktury projektu

0. Ogólne:

- pozostawił puste foldery
- pierwsze co - wywaliło error - długo naprawial
- przyciski stały się funkjconalne
- po za tym brak popraw/popsuć ogólnych
- kod się powtarza - gołym okiem widać

1. Home:

- buttony navigacyjne stały się funkcyjne - przechodzimy do przepisów, listy zakupów i ustawień

2. Przepisy:

- wygląd szczegółów przepisów się zmienił - na gorsze, zniknął przycisk dodawania składników do listy zakupów

3. Lista zakupów:

- zaczęły działać fuinkcje listy, usuwanie, zaznaczanie, odznaczanie

## poprawki ui v1

0. Ogólne:

- usunięcie górnego headera, z tytułem ekrnau, ze wszystkich ekranów

* taby poprawione
* znowu sporo błędów ale poprawił

1. Home:

- pozbyć się nieużywanego ekranu powitalnego - po przejściu na ekrna storna główna ma się pojawić pokaz slajdów
- usunięcię przycisków - przechodzenie za pomocą przesuwania slajdów

* dalej/wstecz działa,
* na koncu przycisk rozpocznij przechodzi do ekranów przepisów

2. Przepisy:

- usunąć gwiazdki/oceny z opisu
- przycisk pokazujący filtry - ikona koła zębatego została zastąppiona literą F - przywrócić ikonę koła zębatego
- szczegóły przepisu - fatalny design - przywrócić trzy kafelki pod obrazkiem dania które informują o :
  - czasie przygotowania,
  - ilości porcji,
  - trudności przygotowania
- w szczegółach przepisu dolny margines jest zbyt duży, ewidetnie skraca dół ekranu - dostosuj ekrna tak aby dosięgał górnego bordera dolnych tabów nawigacji
- potrzeba dodania Infinite scroll-a - czyli doczytywanie kolejnych obiektów po zjechaniu w dół listy (wyszukiwanie tylko dla wczytanych obiektów? uwzględnić filtrowanie dla wszystkich obiektów - imitować żądanie do bazy danych z wybranym filtrem )

* poprawiono wyświeltanie wpisanej frazy w wyszukiwarkę przepisów
* poprawiono wyświetlanie się listy - widac kazdy element nawet po zjechainiu na sam dół listy
* dodano infomrację jakiego typu jest danie (obiad kolacja itp)
* filtry chowają się po klinięciu w inne miejsce przerobione na modal wychodzący od dołu - podoba mi się to
* składniki dodają się poprawnie do listy zakupów, przechodzimy do ekranu

3. Lista zakupów:

- mało widoczny przycisk dodawania w prawym dolnym rogu ( znak plus jest za ciemny)
- przycisk dodawania nowego produktu ma być zamianiony na formularz na samej górze - powodem jest to że obecnie przycisk zasłania ikonę usuwania elementu z listy produktów najniższych elementów na liście
- modal dodawania nowego skłądnika:
- ilość ma nie być opcjonalna,
- ilość ma akceptować tylko liczbowy input
- jednostka ma być dropdown listą z dostępnymi jednostkami
- dodać walidację - przy próbie dodania składnika bez nazwy lub ilości - poinformowac usera że jest to niemożliwe i dlaczego
- zaznaczani działa wybiórczo, raz działa raz nie nie zauważyłem zależności dlaczego działa / nie działa

* usuwanie działa poprawnie
* dodawanie produktu też dziala dobrze

4. ustawienia

- wyglądają bardzo w porządku
- poprawiono wszystko co trzeba było

## Poprawki ui v2:

0. Ogólne:

- nie usunięto headerów z ekranów a tylko zwięksozno górny margin

1. Home:

- nie usunięto starego ekranu home - miał go zastąpić ekran ze sliderami
- na ostanim slioderze nie można cofnąć się do poprzednich - przy próbie przejścia na wcześniejszy slider, przenosimy się na ekren przepisów - to działanie powinno być tylko dla przejścia dalej na osttanim sliderze
- ostatni slider działa bardzo źle, można nawet nie kliknąć nic na ostatnim sliderze i automatycznie zostaniemy przeniesieni do przepisów

2. Przepisy:

- infinite scroll wydaje się nie być zaimplementowany - chciałbym żeby logika wgl nie imituje pobierania danych z backendu, nie widać komunikatu na dole listy że trwa doczytywanie lub że pełna lista została doczytana - dodaj timeout ładowania się danych do listy po zjechaniu na sam dół listy
- obrazki z unplash nie wyświetlają się, dostosuj ekrna tak, aby wyświetlał obrazki z linka unplash
- przycisk dodania produktów do listy zakupów crashuje aplikację

* ikona ustawień w przycisku filtrów została przywrócona
* redesign szczegółów przeszedł pomyślnie
* dolny margines został dostosowany do ekranu, nie jest idealnie ale wygląda to okey

3. Lista zakupów:

- formularz zajmuje bardzo dużo miejsca niech pole ilość i jednostka będą obok siebie w drugim wierszu - to zmniejszy formularz
- dodatkowo, teraz formularz jest "przyklejony" na samą górę, chciałbym, żeby formularz był przewijalną częścią ekranu czyli żeby tez się przeiwjał przy przeiwjaniu listy zakupów

* dodano formularz dodawania nowych produktów,
* walidacja formularza działa poprawnie nie można dodac elementu bez wypełnienia obowiązkowych pół , komunikat pojawia się poprawnie

## Poprawki ui v3:

0. Ogólne:

- ogromne błędy kompilacji, bardzo długie poprawki, żeby uruchomić projekt
- nie usunięto headerów po raz kolejny

1. Home:

- dodanie przycisku rozpocznij oraz usunięcie tabu strona główna - chociaż nie o to chodziło

* nie crashuje się aplikacja nic nie dzieje się automatycznie - przycisk poprawnie przenosi użytkownika do ekrnau przepisów

2. Przepisy:

- infinite scroll nie działa, po przejściuna sam dół listy aplikacja crashuje się
- pobieranie obrazów z unplash też nie działa, przy przefiltrowaniu listy tak, aby pojawił się kafelek z podglądem obrazka z unplash aplikacji crashuje się
- zastąpić ikonę powrotu na button z napisem 'Wróć' lub jakimś podobnym
- po kliknięciu dodaj produkty do listy zakupów, pojawia się komunikat o dodaniu produktów do listy, następnie przy próbie przejścia do ekrnau listy zakupów aplikacja crashuje

3. Lista zakupów:

- ładnie poprawione filtry zgodnie z opisem klienta
- caly ekran jest listą przeiwjalną
- porzednie funkcjonalności zostały zachowane

## Poprawki ui v4:

0. Ogólne:

- nie usunięto hederów z ekranów lista zakupów i ustawienia
- usunięto za dużo górnego marginesu - eknrany nachodza na górny pasek narzedzi telefonu

* usunięto headery z ekranu przepisów

1. Home:

- margines górny jest za mały górne ikony nachodzą na pasek narzędzi telefonu

2. Przepisy:

- ładowanie się obrazka z unplash crashuje aplikację
- infinite scroll - przy doczytywaniu danych aplikacja crashuje - najprawdopodobniej jest to związane z łądowanie obrazka z unplash

* usunbięto header z nazwą ekranu
* poprawnie dodają się produkty do listy zakupów
* dodano przycisk powrotu w widoku szczegółów

## Poprawki Ui v5:

0. Ogólne:

- proces budowania jest naprawiany
- za duży margin górny

2. Przepisy:

- obrazki unplash crashują aplikację przez to nie można dobrze przetestować ekrnau przepisy

## Poprawki UI v6:

- marginy poprawione, wszystko spójne

2. Przepisy:

- obrazki z uplash dalej nie działają - crashują aplikację

## Poprawki ui v7:

- chat uznał że pobieranie grafik z internetu powoduje crash aplikacji (ma rację XD) i zamiast to naprawić zastąpił grafiki ikonkami

## Poprawki UI v8:

- chat dostał logi z androida, dzięki temu mógł zweryfikować w końcu błąd, brak permioossionó aplikacji do dostepu do internetu
- po naprawieniu błędu z crashowaniem aplikacji, możemu w końcu przetestować infinity loader który nie działa

## Poprawki UI v9:

- działa infinite loader
- pora przerobić szczegóły przepisu na modal wychodzący z dołu

## Poprawki ui v10:

- modal wyświetla się do połowy ekranu - zmodyfikuj tak, aby wyświetlał się prawie na cały ekran, został tylko ały margines aby było wiadomo, że
- w modalu nie wyświetla się powiadomienie dodania produktów do listy zakupów
