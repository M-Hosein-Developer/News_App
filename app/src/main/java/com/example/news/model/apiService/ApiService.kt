package com.example.news.model.apiService

import com.example.news.model.dataClass.NewsBySearchResp
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search-news")
    suspend fun getNewsBySearch(
        @Query("api-key") apiKey: String,
        @Query("text") queryText: String,
        @Query("language") language: String
    ) : NewsBySearchResp

}