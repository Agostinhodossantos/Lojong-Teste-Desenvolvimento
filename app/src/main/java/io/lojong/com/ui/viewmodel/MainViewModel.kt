package io.lojong.com.ui.viewmodel

import android.util.MutableInt
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lojong.com.data.FactRepository
import io.lojong.com.model.Result
import io.lojong.com.model.FactResponse
import io.lojong.com.util.SharedPreferencesHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

/**
 * ViewModel for MainActivity
 */
class MainViewModel @ViewModelInject constructor(private val factsRepository: FactRepository) :
        ViewModel() {

    private val _factList = MutableLiveData<Result<FactResponse>>()
    val factsList = _factList

    private val _lastPosition = Int
    var lastPosition = _lastPosition

    init {
        fetchFacts()
    }

    private fun fetchFacts() {
        viewModelScope.launch {
            factsRepository.fetchFacts().collect {
                _factList.value = it as Result<FactResponse>?
            }
        }
    }
}