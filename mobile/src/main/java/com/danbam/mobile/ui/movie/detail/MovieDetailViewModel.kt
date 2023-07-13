package com.danbam.mobile.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.movie.MovieDetailUseCase
import com.danbam.domain.usecase.movie.MoviePeopleDetailUseCase
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
    private val moviePeopleDetailUseCase: MoviePeopleDetailUseCase
) : ContainerHost<MovieDetailState, Unit>, ViewModel() {
    override val container = container<MovieDetailState, Unit>(MovieDetailState())

    fun movieDetail(movieIndex: Int) = intent {
        viewModelScope.launch {
            movieDetailUseCase(movieIndex = movieIndex).onSuccess {
                reduce { state.copy(movieDetailInfo = it) }
            }
        }
    }

    fun moviePeopleDetail(isActor: Boolean, idx: Int) = intent {
        viewModelScope.launch {
            moviePeopleDetailUseCase(if (isActor) "actor" else "director", idx).onSuccess {
                reduce { state.copy(appearanceMovieList = it.movieList) }
            }
        }
    }
}