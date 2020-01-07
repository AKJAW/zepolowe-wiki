# API
Posiada 2 punkty dostępu

#### [random-article](https://wiki-api-us.herokuapp.com/random-article)
Nie przyjmuje on żadnego query i zwraca losowy artykuł z wikipedii razem ze wszystkimi odnosnikami do innych artykułów.

#### [article-from-title](https://wiki-api-us.herokuapp.com/article-from-title)
Przyjmuje jeden query **title** jego wartość to tytuł szukanego artykułu. Zwraca artykuł wraz ze wszystkimi odnosnikami do innych artykułów.

### Moduły
API korzysta z dwóch modułów:

- api.py - odpowiada za łączenie się z Wikimedia API. Udostępnia on metody do losowego artykułu oraz wyszukiwania artykułu.
- scraper.py - ściąga stronę Wikipedii i wyciąga z niej wszystkie występujące odnośniki do innych artykułów.

# Aplikacja Android
Nie zaimplementowane:
- Możliwość wybierania poziomu trudności
- Zapis wyników
- Wybór języków

Z powodu małej ilości zajęć nie udało się zaimplementować wielu funkcji.

Użyte biblioteki:
- Retrofit do łączenia się z API
- Moshi w celu dekodowania zwróconego JSON z API
- Kotlin Coroutines do wywoływania połączeń do API na tylnim wątku
- Glide do ładowania i pokazywania obrazków z internetu
- Android Lifecycle w celu połączenia UI oraz API za pomocą ViewModel. LiveData do udostępnienia danych zwróconych z API do UI.

Na chwilę obecną:

### UI
Aplikacja posiada jeden Activity który pokazuje jeden fragment **GameFragment** zajmujący cały ekran. W tym fragmencie znajduje się cały wygląd oraz logika z nią zwiazana.

##### ArticleView
Jest to View (Widok) stworzony specjalnie do wyświetlania informacji o artykule. Posiada on dwa stany: 
- collapsed - zdjęcie jest miniaturką a jedynym tekstem jest tytuł.
- expanded - zdjęcie jest większe, pod nim znajduje się tytuł oraz opis który można przewijać.

Posiada dwie publiczne metody:
```kotlin
fun setIsLoading(newIsLoading: Boolean)
```
Służy do pokazania oraz ukrywania okrągłego wskaźnika łądowania



```kotlin
fun setArticle(article: Article, shouldChangeHeaderColor: Boolean = false)
```
Przyjmuje on nowy artykuł który ma być pokazany w widoku oraz zmienną która decyduje czy za każdą tą zmianą powinien zostać zmieniony kolor tła nagłówka.

### ViewModel
Służy do połączenia środowiska Android z logiką biznesową. W tej aplikacji istnieje tylko jeden ViewModel (GameViewModel). Jego zadaniem jest połączenie API oraz UI, pozwala on łączyć się z API oraz przechowuje zwrócone dane.

### Pakiet Network
W nim znajduje się logika do robienia połączenia z API. Użyta jest tutaj biblioteka Retrofit który w łatwy sposób pozwala na tworzenie interfejsów do łączenia się z API.
```kotlin
interface WikiApiService {
    @GET("random-article")
    suspend fun randomArticle(): WikiApiResponse

    @GET("article-from-title")
    suspend fun articleFromTitle(@Query("title") title: String): WikiApiResponse
}
```
Retrofit dodatkowo pozwala na integrację z biblioteką Moshi. Dzięki temu nie trzeba ręcznie przypisywać wartości zwróconego JSON a wystarczy dostarczyć klasę której pola pokrywają się z tymi z JSON.
```kotlin
@JsonClass(generateAdapter = true)
data class WikiArticleResponse(
    @field:Json(name = "name") val name: String = "",
    @field:Json(name = "description") val description: String = "",
    @field:Json(name = "image") val image: String? = null,
    @field:Json(name = "url") val url: String = ""
)

@JsonClass(generateAdapter = true)
data class WikiApiResponse(
    @field:Json(name = "article") val article: WikiArticleResponse,
    @field:Json(name = "links") val links: List<String> = listOf()
)
```
