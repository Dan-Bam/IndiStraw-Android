package com.danbam.tv.ui.movie.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.MovieHistoryParam
import com.danbam.domain.usecase.movie.AddMovieHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MoviePlayViewModel @Inject constructor(
    private val addMovieHistoryUseCase: AddMovieHistoryUseCase
) : ContainerHost<Unit, Unit>, ViewModel() {
    override val container = container<Unit, Unit>(Unit)

    fun addMovieHistory(movieIdx: Int, position: Float) = intent {
        viewModelScope.launch {
            addMovieHistoryUseCase(
                movieHistoryParam = MovieHistoryParam(
                    movieIdx = movieIdx,
                    historyTime = position
                )
            )
        }
    }
}