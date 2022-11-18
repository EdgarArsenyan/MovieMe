package com.example.data.api

import com.example.data.util.AppConstants.EMPTY_STRING
import com.example.data.util.toMovieModel
import com.example.data.util.toSearchModel
import com.example.domain.model.MovieModel
import com.example.domain.model.Search
import com.example.domain.model.SearchModel
import com.example.domain.repo.MovieRepository

class MovieApiRepoImpl(private val movieApi: MovieApi) : MovieRepository {

    override suspend fun getMovieByTitle(
        apiKey: String,
        title: String,
        page: String
    ): SearchModel? = try {
        movieApi.getByTitle(apiKey, title, page).body()?.toSearchModel()
    } catch (e: Exception) {
        SearchModel(
            EMPTY_STRING,
            mutableListOf(
                Search(
                    EMPTY_STRING,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    EMPTY_STRING,
                    EMPTY_STRING
                )
            ), EMPTY_STRING
        )
    }

    override suspend fun getMovieById(
        apiKey: String,
        imdbID: String,
        plot: String
    ): MovieModel? =
        movieApi.getById(apiKey, imdbID, plot).body()?.toMovieModel()
}