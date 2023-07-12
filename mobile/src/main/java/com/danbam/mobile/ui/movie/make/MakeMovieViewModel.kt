package com.danbam.mobile.ui.movie.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.file.SendFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeMovieViewModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
) : ContainerHost<Unit, Unit>, ViewModel() {
    override val container = container<Unit, Unit>(Unit)

    fun uploadFile(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                onUploaded(it.file)
            }
        }
    }
}