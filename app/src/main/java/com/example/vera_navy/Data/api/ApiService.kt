package com.example.vera_navy.api

import com.example.vera_navy.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Memakai API Publik lokal yang sudah terbukti berhasil di project sebelahmu
    @GET("https://berita-indo-api-next.vercel.app/api/antara-news/terkini")
    fun getTopHeadlines(): Call<NewsResponse>
}