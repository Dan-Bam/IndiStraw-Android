package com.danbam.indistraw.feature.tv.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.entity.search.RecentSearchEntity
import com.danbam.indistraw.core.domain.usecase.movie.MoviePopularListUseCase
import com.danbam.indistraw.core.domain.usecase.search.GetRecentSearchUseCase
import com.danbam.indistraw.core.domain.usecase.search.GetRelatedSearchUseCase
import com.danbam.indistraw.core.domain.usecase.search.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecentSearchUseCase: GetRecentSearchUseCase,
    private val getRelatedSearchUseCase: GetRelatedSearchUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val moviePopularListUseCase: MoviePopularListUseCase
) : ContainerHost<SearchState, Unit>, ViewModel() {
    override val container = container<SearchState, Unit>(SearchState())

    fun getRecentSearch() = intent {
        viewModelScope.launch {
            getRecentSearchUseCase().onSuccess {
                reduce { state.copy(relatedSearchList = it.map { it.search }) }
            }
        }
    }

    fun getRelatedSearch(keyword: String) = intent {
        viewModelScope.launch {
            getRelatedSearchUseCase(keyword = keyword).onSuccess {
                reduce { state.copy(relatedSearchList = it) }
            }
        }
    }

    fun moviePopularList() = intent {
        viewModelScope.launch {
            moviePopularListUseCase().onSuccess {

            }
        }
    }

    fun search(keyword: String) = intent {
        viewModelScope.launch {
            searchMovieUseCase(recentSearchEntity = RecentSearchEntity(search = keyword)).onSuccess {
                reduce { state.copy(resultPager = it) }
            }
        }
    }
}