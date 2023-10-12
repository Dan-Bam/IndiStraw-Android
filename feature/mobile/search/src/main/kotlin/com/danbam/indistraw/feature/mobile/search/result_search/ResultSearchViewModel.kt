package com.danbam.indistraw.feature.mobile.search.result_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.entity.search.RecentSearchEntity
import com.danbam.indistraw.core.domain.usecase.search.SearchFundingUseCase
import com.danbam.indistraw.core.domain.usecase.search.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ResultSearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val searchFundingUseCase: SearchFundingUseCase
) : ContainerHost<ResultSearchState, Unit>, ViewModel() {
    override val container = container<ResultSearchState, Unit>(ResultSearchState())

    fun searchMovie(keyword: String) = intent {
        reduce { state.copy(fundingPager = null) }
        viewModelScope.launch {
            searchMovieUseCase(recentSearchEntity = RecentSearchEntity(search = keyword)).onSuccess {
                reduce { state.copy(moviePager = it) }
            }
        }
    }

    fun searchFunding(keyword: String) = intent {
        reduce { state.copy(moviePager = null) }
        viewModelScope.launch {
            searchFundingUseCase(recentSearchEntity = RecentSearchEntity(search = keyword)).onSuccess {
                reduce { state.copy(fundingPager = it) }
            }
        }
    }
}