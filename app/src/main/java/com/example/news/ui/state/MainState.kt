package com.example.news.ui.state

import com.example.news.model.dataClass.NewsBySearchResp

sealed class MainState() {

    object Idle : MainState()
    object Loading : MainState()
    data class NewsSearch(val news : List<NewsBySearchResp.New>) : MainState()
    data class Error(val error : String?) : MainState()

}