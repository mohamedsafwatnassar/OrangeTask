package com.orange.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orange.domain.entity.NewsResponse
import com.orange.domain.usecase.GetNewsUseCase
import com.orange.domain.utils.ResponseHandler
import com.orange.newsapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    // empty list

    private val _getNewsMutableState: MutableLiveData<ResponseHandler<NewsResponse?>?> =
        MutableLiveData()
    val getNewsLiveState: LiveData<ResponseHandler<NewsResponse?>?> = _getNewsMutableState
    private var job: Job? = null

    init {
        getNewsList()
    }

//    val userFlow = getNewsUseCase(searchText)
//        .flowOn(Dispatchers.IO) // Specify background thread
//        .onEach { /* Update UI with user data */ }
//        .catch { /* Handle error */ }.asLiveData()

    fun getNewsList(searchQuery: String = "") {
        var searchText = Constant.All
        if (searchQuery.isNotEmpty()) {
            searchText = searchQuery
        }
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            _getNewsMutableState.postValue(getNewsUseCase(searchText))
        }
    }
}
