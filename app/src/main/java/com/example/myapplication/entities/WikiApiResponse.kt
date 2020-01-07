package com.example.myapplication.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WikiApiResponse(
    @field:Json(name = "article") val article: WikiArticleResponse,
    @field:Json(name = "links") val links: List<String> = listOf()
)