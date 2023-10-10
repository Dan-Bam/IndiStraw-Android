package com.danbam.mobile.ui.search.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ContainerHost<SearchState, Unit>, ViewModel() {
    override val container = container<SearchState, Unit>(SearchState())

    fun changeSearchType(searchType: SearchType, keyword: String = "") = intent {
        reduce { state.copy(searchType = searchType) }
        if (searchType is SearchType.Result) {
            reduce { state.copy(keyword = keyword) }
        }
    }
}