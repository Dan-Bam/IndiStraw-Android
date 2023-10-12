package com.danbam.indistraw.app.mobile.ui.search.start_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.usecase.search.GetRecentSearchUseCase
import com.danbam.indistraw.core.domain.usecase.search.PopularTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StartSearchViewModel @Inject constructor(
    private val getRecentSearchUseCase: GetRecentSearchUseCase,
    private val popularTagUseCase: PopularTagUseCase
) : ContainerHost<StartSearchState, Unit>, ViewModel() {
    override val container = container<StartSearchState, Unit>(StartSearchState())

    fun getPopularTag() = intent {
        viewModelScope.launch {
            popularTagUseCase().onSuccess {
                reduce { state.copy(popularTagList = it) }
            }
        }
    }

    fun getRecentSearch() = intent {
        viewModelScope.launch {
            getRecentSearchUseCase().onSuccess {
                reduce { state.copy(recentSearchList = it) }
            }
        }
    }
}