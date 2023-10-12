package com.danbam.indistraw.app.mobile.ui.main.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.component.MovieTab
import com.danbam.indistraw.core.domain.usecase.account.GetProfileUseCase
import com.danbam.indistraw.core.domain.usecase.banner.GetBannerUseCase
import com.danbam.indistraw.core.domain.usecase.crowd_funding.FundingPopularListUseCase
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
class MainViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val fundingPopularListUseCase: FundingPopularListUseCase,
    private val moviePopularListUseCase: MoviePopularListUseCase,
    private val movieRecommendListUseCase: MovieRecommendListUseCase,
    private val movieRecentListUseCase: MovieRecentListUseCase,
    private val getBannerUseCase: GetBannerUseCase,
) : ContainerHost<MainState, Unit>, ViewModel() {
    override val container = container<MainState, Unit>(MainState())

    fun getProfile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce { state.copy(profileUrl = it.profileUrl) }
            }
        }
    }

    fun getBanner() = intent {
        viewModelScope.launch {
            getBannerUseCase().onSuccess {
                reduce { state.copy(bannerList = it) }
            }
        }
    }

    fun fundingPopularLst() = intent {
        viewModelScope.launch {
            fundingPopularListUseCase().onSuccess {
                reduce { state.copy(fundingPopularList = it) }
            }
        }
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