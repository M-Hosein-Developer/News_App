package com.example.news.util

import com.example.news.model.dataClass.NewsBySearchResp

const val BASE_URL = "https://api.worldnewsapi.com/"
const val API_KEY = "528ae02b1d9345038d0564f75f35a2bf"
const val LANGUAGE = "en"

val EMPTY_DATA = NewsBySearchResp.New(
    "" , listOf("" ,"") , 0 , "" , "" , "" , 0.0 ,
    "" , "" , "" , ""
)