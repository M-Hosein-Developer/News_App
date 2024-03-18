package com.example.news.model.repository

import com.example.news.model.apiService.ApiService
import com.example.news.model.dataClass.NewsBySearchResp
import com.example.news.util.API_KEY
import com.example.news.util.LANGUAGE
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService : ApiService) : MainRepository {


    override suspend fun getNewsBySearch(queryText: String): List<NewsBySearchResp.New>
    = apiService.getNewsBySearch(API_KEY , queryText , LANGUAGE).news


}