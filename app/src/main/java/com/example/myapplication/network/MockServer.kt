package com.example.myapplication.network

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.entities.Article
import kotlinx.coroutines.Job
import kotlin.random.Random

class MockServer(): WikiApi{
    val articles = createArticles()


    override fun randomArticle(articleLiveData: MutableLiveData<Article>): Job? {
        val randomIndex = Random.nextInt(articles.size)
        articleLiveData.postValue(articles[randomIndex])

        return null
    }

    override fun articleFromTitle(title: String, articleLiveData: MutableLiveData<Article>): Job? {
        val randomIndex = Random.nextInt(articles.size)
        articleLiveData.postValue(articles[randomIndex])

        return null
    }

    private fun createArticles(): List<Article> {
        val links1 = createLinks(
            "Ziemia",
            "Pa%C5%84stwo",
            "Jamajka",
            "Saint_Thomas_(Jamajka)",
            "Ziemia",
            "Jamajka"
        )
        val article1 = Article("Morant Bay",
            "Morant Bay – miasto w południowo-wschodniej Jamajce w hrabstwie Surrey. Miasto jest stolicą regionu Saint Thomas. W 1865 roku doszło w miejscowości do jednej z większych rebelii w historii Jamajki wznieconej przez niewolników .\n\n\n== Miasta partnerskie ==\n Hartford, Stany Zjednoczone",
            "https://upload.wikimedia.org/wikipedia/commons/3/32/Morant_Bay%2C_Jamaika.jpg",
            links1)


        return listOf(article1)
    }

    private fun createLinks(vararg strings: String): List<String>{
        return strings.map {
            it
        }
    }

}