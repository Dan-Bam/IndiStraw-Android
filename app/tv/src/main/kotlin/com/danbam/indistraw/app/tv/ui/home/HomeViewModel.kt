package com.danbam.indistraw.app.tv.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.component.MovieTab
import com.danbam.indistraw.core.domain.usecase.banner.GetBannerUseCase
import com.danbam.indistraw.core.domain.usecase.movie.MoviePopularListUseCase
import com.danbam.indistraw.core.domain.usecase.movie.MovieRecentListUseCase
import com.danbam.indistraw.core.domain.usecase.movie.MovieRecommendListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviePopularListUseCase: MoviePopularListUseCase,
    private val movieRecommendListUseCase: MovieRecommendListUseCase,
    private val movieRecentListUseCase: MovieRecentListUseCase,
    private val getBannerUseCase: GetBannerUseCase,
) : ContainerHost<HomeState, Unit>, ViewModel() {
    override val container = container<HomeState, Unit>(HomeState())

    fun getBanner() = intent {
        viewModelScope.launch {
            getBannerUseCase().onSuccess {
                reduce { state.copy(bannerList = it) }
            }
        }
    }

    fun saveCurrentIndex(index: Long) = intent {
        reduce { state.copy(currentMovieIndex = index) }
    }

    fun movieList(movieType: MovieTab) = intent {
        viewModelScope.launch {
            when (movieType) {
                is MovieTab.PopularMovie -> {
                    moviePopularListUseCase().onSuccess {
                        reduce { state.copy(movieList = it) }
                    }
                }

                is MovieTab.RecommendMovie -> {
                    movieRecommendListUseCase().onSuccess {
                        reduce { state.copy(movieList = it) }
                    }
                }

                else -> {
                    movieRecentListUseCase().onSuccess {
                        reduce { state.copy(movieList = it) }
                    }
                }
            }
        }
    }
}