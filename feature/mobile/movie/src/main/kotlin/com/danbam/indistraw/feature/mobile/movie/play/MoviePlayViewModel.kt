package com.danbam.indistraw.feature.mobile.movie.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.param.movie.MovieHistoryParam
import com.danbam.indistraw.core.domain.usecase.movie.AddMovieHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MoviePlayViewModel @Inject constructor(
    private val addMovieHistoryUseCase: AddMovieHistoryUseCase
) : ContainerHost<Unit, MoviePlaySideEffect>, ViewModel() {
    override val container = container<Unit, MoviePlaySideEffect>(Unit)

    fun addMovieHistory(movieIdx: Long, position: Float) = intent {
        viewModelScope.launch {
            addMovieHistoryUseCase(
                movieHistoryParam = MovieHistoryParam(
                    movieIdx = movieIdx,
                    historyTime = position
                )
            ).onSuccess {
                postSideEffect(MoviePlaySideEffect.SuccessSaveHistory)
            }
        }
    }
}