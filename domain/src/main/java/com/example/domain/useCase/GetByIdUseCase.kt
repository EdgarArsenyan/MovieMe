package com.example.domain.useCase

import com.example.domain.model.MovieModel
import com.example.domain.repo.MovieRepository

class GetByIdUseCase(private val movieRepo: MovieRepository) {

    suspend fun execute(
        apiKey: String,
        imdbID: String,
        plot: String
    ): MovieModel? = movieRepo.getMovieById(apiKey, imdbID, plot)
}