package com.orange.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orange.domain.entity.Article
import com.orange.domain.usecase.GetNewsUseCase
import com.orange.newsapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private var getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private var job: Job? = null

    fun getNewsLiveData(searchQuery: String = ""): LiveData<PagingData<Article>> {
        var searchText = Constant.All
        if (searchQuery.isNotEmpty()) {
            searchText = searchQuery
        }
        job?.cancel()
        return getNewsUseCase(searchText)
            .cachedIn(viewModelScope)
            .asLiveData()
    }

    // private val _getNewsMutableState: MutableLiveData<ResponseHandler<NewsResponse?>?> = MutableLiveData()
    // val getNewsLiveState: LiveData<ResponseHandler<NewsResponse?>?> = _getNewsMutableState

//    fun getNewsList(searchQuery: String = "") {
//        var searchText = Constant.All
//        if (searchQuery.isNotEmpty()) {
//            searchText = searchQuery
//        }
//        job?.cancel()
//        job = viewModelScope.launch(Dispatchers.IO) {
//            _getNewsMutableState.postValue(getNewsUseCase(searchText))
//        }
//    }
}

//    fun getPassengers() = Pager(
//        config = PagingConfig(
//            pageSize = 4,
//            enablePlaceholders = false
//        ),
//        pagingSourceFactory = { getNewsUseCase("a", 1) }
//    ).liveData

//    private val _getNewsMutableState: MutableStateFlow<PagingData<Article>?> = MutableStateFlow()
//    val getNewsLiveState: StateFlow<PagingData<Article>?> = _getNewsMutableState

//    fun getNews(searchQuery: String = "") = viewModelScope.launch(Dispatchers.IO) {
//        try {
//            val flow = Pager(
//                PagingConfig(
//                    pageSize = 5,
//                    enablePlaceholders = false
//                )
//            ) {
//                var searchText = Constant.All
//                if (searchQuery.isNotEmpty()) {
//                    searchText = searchQuery
//                }
//                NewsPagingSource(
//                    getNewsUseCase,
//                    searchText
//                )
//            }.flow.cachedIn(viewModelScope)
//
//            withContext(Dispatchers.IO) {
//                _getNewsMutableState.emit(flow)
//            }
//        } catch (e: Exception) {
//        }
//    }
