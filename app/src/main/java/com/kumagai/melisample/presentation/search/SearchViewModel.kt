package com.kumagai.melisample.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumagai.melisample.R
import com.kumagai.melisample.domain.use_case.SearchUseCase
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
                            SearchState.Error(
                                R.string.empty_search_title,
                                R.string.empty_search_subtitle
                            )
                        } else {
                            SearchState.Success(it.results)
                        }
                    )
                }
                .onFailure {
                    _searchLiveData.postValue(
                        SearchState.Error(
                            R.string.error_search_title,
                            R.string.error_search_subtitle,
                            R.color.error_red
                        )
                    )
                }
        }
    }
}
