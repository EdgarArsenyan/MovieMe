package com.example.domain.model

data class SearchModel(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)