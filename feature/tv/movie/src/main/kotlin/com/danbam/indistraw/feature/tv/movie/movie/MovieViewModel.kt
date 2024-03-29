package com.danbam.indistraw.feature.tv.movie.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.component.MovieGenre
import com.danbam.indistraw.core.domain.usecase.movie.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
) : ContainerHost<MovieState, Unit>, ViewModel() {
    override val container = container<MovieState, Unit>(MovieState())

    fun saveCurrentIndex(index: Long) = intent {
        reduce { state.copy(currentMovieIndex = index) }
    }

    fun movieList(movieGenre: MovieGenre) = intent {
        viewModelScope.launch {
            movieListUseCase(movieGenre.genre).onSuccess {
                reduce { state.copy(movieAllPager = it) }
            }
        }
    }
}