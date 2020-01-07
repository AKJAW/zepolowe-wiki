package com.example.myapplication.game_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.entities.Article
import com.example.myapplication.network.WikiApi
import com.example.myapplication.network.WikiApiImpl

class GameViewModel(private val wikiApi: WikiApi = WikiApiImpl()): ViewModel(){
    private val _targetArticle = MutableLiveData<Article>()
    val targetArticle: LiveData<Article> = _targetArticle

    private val _currentArticle = MutableLiveData<Article>()
    val currentArticle: LiveData<Article> = _currentArticle

    fun getRandomArticleForTarget(){
        wikiApi.randomArticle(_targetArticle)
    }

    fun getArticleFromTitleForTarget(title: String){
        wikiApi.articleFromTitle(title, _targetArticle)
    }

    fun getRandomArticleForCurrent(){
        wikiApi.randomArticle(_targetArticle)
    }

    fun getArticleFromTitleForCurrent(title: String){
        wikiApi.articleFromTitle(title, _targetArticle)
    }
}