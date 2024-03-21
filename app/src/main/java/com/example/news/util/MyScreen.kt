package com.example.news.util

sealed class MyScreen(val route : String) {

    object NewsSearch : MyScreen("NewsSearch")

}