package com.example.domain.repo

import com.example.domain.model.MovieModel
import com.example.domain.model.SearchModel

interface MovieRepository {

    suspend fun getMovieByTitle(
        apiKey: String,
        title: String,
        page: String
    ): SearchModel?

    suspend fun getMovieById(
        apiKey: String,
        imdbID: String,
        plot: String
    ): MovieModel?
}