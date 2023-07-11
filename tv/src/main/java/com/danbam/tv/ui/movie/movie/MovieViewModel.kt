package com.danbam.tv.ui.movie.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(

) : ContainerHost<HomeState, Unit>, ViewModel() {
    override val container = container<HomeState, Unit>(HomeState())

    fun saveCurrentIndex(index: Int) = intent {
        reduce { state.copy(currentMovieIndex = index) }
    }
}