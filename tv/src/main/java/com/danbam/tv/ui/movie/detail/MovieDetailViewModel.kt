package com.danbam.tv.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.movie.MovieDetailUseCase
import com.danbam.domain.usecase.movie.MovieHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val movieHistoryUseCase: MovieHistoryUseCase,
) : ContainerHost<MovieDetailState, Unit>, ViewModel() {
    override val container = container<MovieDetailState, Unit>(MovieDetailState())

    fun movieHistory(movieIndex: Long) = intent {
        viewModelScope.launch {
            movieHistoryUseCase(movieIdx = movieIndex).onSuccess {
                reduce { state.copy(moviePosition = it.historyTime) }
            }
        }
    }

    fun movieDetail(movieIndex: Long) = intent {
        viewModelScope.launch {
            movieDetailUseCase(movieIndex = movieIndex).onSuccess {
                reduce { state.copy(movieDetailInfo = it) }
            }
        }
    }
}