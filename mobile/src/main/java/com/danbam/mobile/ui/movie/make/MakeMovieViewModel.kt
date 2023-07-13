package com.danbam.mobile.ui.movie.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.file.SendFileUseCase
import com.danbam.mobile.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeMovieViewModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
) : ContainerHost<MakeMovieState, Unit>, ViewModel() {
    override val container = container<MakeMovieState, Unit>(MakeMovieState())

    fun uploadFile(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                onUploaded(it.file)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun saveIntroduce(
        thumbnailUrl: String?,
        movieUrl: String?,
        title: String,
        description: String,
        isFunding: Boolean,
        onNext: () -> Unit
    ) = intent {
        if (thumbnailUrl.isNullOrBlank()) return@intent
        else if (movieUrl.isNullOrBlank()) return@intent
        else if (title.isEmpty()) return@intent
        else if (description.isEmpty()) return@intent
        else {
            reduce {
                state.copy(
                    thumbnailUrl = thumbnailUrl,
                    movieUrl = movieUrl,
                    title = title,
                    description = description,
                    isFunding = isFunding
                )
            }
            onNext()
        }
    }
}