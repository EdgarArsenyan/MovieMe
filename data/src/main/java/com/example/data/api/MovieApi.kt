package com.example.data.api

import com.example.data.model.MovieModelData
import com.example.data.model.SearchModelData
import com.example.data.util.AppConstants.EMPTY_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val apikey = "apikey"
const val imdb = "i"
const val title = "s"
const val plot = "plot"
const val page = "page"

interface MovieApi {

    @GET(EMPTY_URL)
    suspend fun getByTitle(
        @Query(apikey) apiKey: String,
        @Query(title)  titleName: String,
        @Query(page)   plots: String,
    ): Response<SearchModelData>

    @GET(EMPTY_URL)
    suspend fun getById(
        @Query(apikey) apiKey: String,
        @Query(imdb) imdbID: String,
        @Query(plot) plots: String,
    ): Response<MovieModelData>
}