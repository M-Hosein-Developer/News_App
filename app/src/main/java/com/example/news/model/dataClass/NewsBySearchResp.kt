package com.example.news.model.dataClass

data class NewsBySearchResp(
    val available: Int,
    val news: List<New>,
    val number: Int,
    val offset: Int
) {
    data class New(
        val author: String,
        val authors: List<String>,
        val id: Int,
        val image: String,
        val language: String,
        val publish_date: String,
        val sentiment: Double,
        val source_country: String,
        val text: String,
        val title: String,
        val url: String
    )
}