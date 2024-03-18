package com.example.news.model.repository

import com.example.news.model.dataClass.NewsBySearchResp
import javax.inject.Inject

interface MainRepository {

    suspend fun getNewsBySearch(queryText: String) : List<NewsBySearchResp.New>

}