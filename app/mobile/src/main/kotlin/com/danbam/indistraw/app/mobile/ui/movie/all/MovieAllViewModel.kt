package com.danbam.indistraw.app.mobile.ui.movie.all

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
class MovieAllViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase
) : ContainerHost<MovieAllState, Unit>, ViewModel() {
    override val container = container<MovieAllState, Unit>(MovieAllState())

    fun movieList(movieGenre: MovieGenre) = intent {
        viewModelScope.launch {
            movieListUseCase(movieGenre.genre).onSuccess {
                reduce { state.copy(movieAllPager = it) }
            }
        }
    }
}