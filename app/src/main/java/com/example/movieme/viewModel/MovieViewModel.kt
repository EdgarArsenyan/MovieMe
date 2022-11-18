package com.example.movieme.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.util.AppConstants.API_KEY
import com.example.data.util.AppConstants.EMPTY_STRING
import com.example.domain.model.MovieModel
import com.example.domain.model.SearchModel
import com.example.domain.useCase.GetByIdUseCase
import com.example.domain.useCase.GetByTitleUseCase
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getByIdUseCase: GetByIdUseCase,
    private val getByTitleUseCase: GetByTitleUseCase
) : ViewModel() {

    private val searchLiveData = MutableLiveData<SearchModel>()
    private val movieModelLiveData = MutableLiveData<MovieModel>()

    val searchResult: LiveData<SearchModel> = searchLiveData
    val movieResult: LiveData<MovieModel> = movieModelLiveData

    fun loadBySearch(
        title: String,
        page: String
    ) {
        viewModelScope.launch {
            searchLiveData.value =
                getByTitleUseCase.execute(API_KEY, title, if (page.isEmpty().not()) page else EMPTY_STRING)
        }
    }

    fun loadById(
        imdbID: String,
        plot: String
    ) {
        viewModelScope.launch {
            movieModelLiveData.value = getByIdUseCase.execute(API_KEY, imdbID, plot)
        }
    }
}