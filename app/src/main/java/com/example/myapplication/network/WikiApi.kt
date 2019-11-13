package com.example.myapplication.network

import com.example.myapplication.entities.Article

interface WikiApi{
    fun randomArticle(): Article
    fun articleFromTitle(title: String): Article
}