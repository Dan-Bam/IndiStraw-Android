package com.danbam.indistraw.mobile.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.domain.usecase.movie.MovieDetailUseCase
import com.danbam.indistraw.domain.usecase.movie.MovieHistoryUseCase
import com.danbam.indistraw.domain.usecase.movie.MoviePeopleDetailUseCase
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
    private val moviePeopleDetailUseCase: MoviePeopleDetailUseCase,
    private val movieHistoryUseCase: MovieHistoryUseCase,
) : ContainerHost<MovieDetailState, Unit>, ViewModel() {
    override val container = container<MovieDetailState, Unit>(MovieDetailState())

    fun movieDetail(movieIdx: Long) = intent {
        viewModelScope.launch {
            movieDetailUseCase(movieIdx = movieIdx).onSuccess {
                reduce { state.copy(movieDetailInfo = it) }
            }
        }
    }

    fun movieHistory(movieIdx: Long) = intent {
        movieHistoryUseCase(movieIdx = movieIdx).onSuccess {
            reduce { state.copy(moviePosition = it.historyTime) }
        }
    }

    fun moviePeopleDetail(isActor: Boolean, actorIdx: Long) = intent {
        viewModelScope.launch {
            moviePeopleDetailUseCase(
                actorType = if (isActor) "actor" else "director",
                actorIdx = actorIdx
            ).onSuccess {
                reduce { state.copy(appearanceMovieList = it.movieList) }
            }
        }
    }
}