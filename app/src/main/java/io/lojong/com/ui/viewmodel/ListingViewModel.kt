package io.lojong.com.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lojong.com.data.FactRepository
import io.lojong.com.model.Result
import io.lojong.com.model.FactResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

/**
 * ViewModel for ListingActivity
 */
class ListingViewModel @ViewModelInject constructor(private val movieRepository: FactRepository) :
        ViewModel() {

    private val _movieList = MutableLiveData<Result<FactResponse>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchFacts().collect {
                _movieList.value = it as Result<FactResponse>?
            }
        }
    }
}