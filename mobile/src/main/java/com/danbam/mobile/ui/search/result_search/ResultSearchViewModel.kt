package com.danbam.mobile.ui.search.result_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.entity.RecentSearchEntity
import com.danbam.domain.usecase.search.SearchMovieUseCase
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
) : ContainerHost<ResultSearchState, Unit>, ViewModel() {
    override val container = container<ResultSearchState, Unit>(ResultSearchState())

    fun searchMovie(keyword: String) = intent {
        viewModelScope.launch {
            searchMovieUseCase(recentSearchEntity = RecentSearchEntity(search = keyword)).onSuccess {
                reduce { state.copy(moviePager = it) }
            }
        }
    }
}