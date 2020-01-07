package com.example.myapplication.network

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.entities.Article
import com.example.myapplication.entities.WikiApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

interface WikiApi{
    fun randomArticle(articleLiveData: MutableLiveData<Article>): Job?
    fun articleFromTitle(title: String, articleLiveData: MutableLiveData<Article>): Job?
}

class WikiApiImpl: WikiApi{

    private val wikiApiService = WikiApiServiceProvider.wikiApiService
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun randomArticle(articleLiveData: MutableLiveData<Article>): Job {
        return scope.launch {
            val response = wikiApiService.randomArticle()
            val article = response.toArticle()
            articleLiveData.postValue(article)
        }
    }

    override fun articleFromTitle(title: String, articleLiveData: MutableLiveData<Article>): Job {
        return scope.launch {
            val response = wikiApiService.articleFromTitle(title)
            val article = response.toArticle()
            articleLiveData.postValue(article)
        }
    }

    private fun WikiApiResponse.toArticle(): Article{
        return Article(
            name = this.article.name,
            description = this.article.description,
            image = this.article.image?: "",
            links = this.links
        )
    }

}

fun main() {
    val a = WikiApiImpl()
    val livedata = MutableLiveData<Article>()
    a.randomArticle(livedata)

    sleep(10000)

}