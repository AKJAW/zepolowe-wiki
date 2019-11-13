package com.example.myapplication.entities

data class Article(
    val name: String,
    val description: String,
    val image: String,
    val links: List<WikiLink>
)