package com.example.data.util

import com.example.data.model.MovieModelData
import com.example.data.model.SearchData
import com.example.data.model.SearchModelData
import com.example.data.util.AppConstants.EMPTY_STRING
import com.example.domain.model.MovieModel
import com.example.domain.model.Search
import com.example.domain.model.SearchModel

fun MovieModelData.toMovieModel(): MovieModel = MovieModel(
    Poster = Poster,
    Title = Title,
    Type = Type,
    Year = Year,
    imdbID = imdbID,
    Response = Response,
)

fun SearchData.toSearch(): Search = Search(
    Poster = Poster,
    Title = Title,
    Type = Type,
    Year = Year,
    imdbID = imdbID,
)

fun SearchModelData.toSearchModel(): SearchModel = SearchModel(
    Response = Response,
    Search = Search.map { it.toSearch() },
    totalResults = totalResults
)