package com.example.domain.useCase

import com.example.domain.model.SearchModel
import com.example.domain.repo.MovieRepository

class GetByTitleUseCase(private val movieRepo: MovieRepository) {

    suspend fun execute(
        apiKey: String,
        title: String,
        page: String
    ): SearchModel? = movieRepo.getMovieByTitle(apiKey, title, page)
}