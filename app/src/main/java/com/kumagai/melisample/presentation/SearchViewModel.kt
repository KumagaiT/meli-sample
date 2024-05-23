package com.kumagai.melisample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumagai.melisample.domain.model.SearchResultItem
import com.kumagai.melisample.domain.use_case.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchLiveData = MutableLiveData<SearchState>()
    val searchLiveData: LiveData<SearchState> get() = _searchLiveData

    fun searchItems(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchLiveData.postValue(SearchState.Loading)
            searchUseCase.invoke(query)
                .onSuccess {
                    _searchLiveData.postValue(
                        if (it.results.isEmpty()) {
                            SearchState.Empty
                        } else {
                            SearchState.Success(it.results)
                        }
                    )
                }
                .onFailure {
                    _searchLiveData.postValue(SearchState.Error)
                }
        }
    }
}
