package com.example.vera_navy.model

data class NewsResponse(
    val message: String?,
    val total: Int?,
    val data: List<Article>
)

data class Article(
    val title: String?,
    val link: String?,
    val contentSnippet: String?,
    val isoDate: String?,
    // PERBAIKAN: Ubah dari objek ImageDetails menjadi String biasa
    val image: String?
)
