package com.example.news.ui.intent

sealed class MainIntent {

    data class GetNewsBySearch(val search : String) : MainIntent()

}