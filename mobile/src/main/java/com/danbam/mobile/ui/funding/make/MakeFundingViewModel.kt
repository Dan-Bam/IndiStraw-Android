package com.danbam.mobile.ui.funding.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.file.SendFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeFundingViewModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase
) : ContainerHost<MakeFundingState, Unit>, ViewModel() {
    override val container = container<MakeFundingState, Unit>(MakeFundingState())

    fun uploadImage(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                onUploaded(it.file)
            }
        }
    }

    fun saveIntroduce(
        thumbnailUrl: String?,
        title: String,
        description: String,
        imageList: List<String>,
        onNext: () -> Unit
    ) = intent {
        if (thumbnailUrl.isNullOrBlank()) return@intent
        else if (title.isEmpty()) return@intent
        else if (description.isEmpty()) return@intent
        else if (imageList.isEmpty()) return@intent
        else {
            reduce {
                state.copy(
                    thumbnailUrl = thumbnailUrl,
                    title = title,
                    description = description,
                    imageList = imageList
                )
            }
            onNext()
        }
    }
}