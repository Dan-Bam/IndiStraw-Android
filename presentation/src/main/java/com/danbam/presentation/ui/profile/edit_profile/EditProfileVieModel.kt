package com.danbam.presentation.ui.profile.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.file.SendFileUseCase
import com.danbam.presentation.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileVieModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
) : ContainerHost<Unit, EditProfileSideEffect>, ViewModel() {
    override val container = container<Unit, EditProfileSideEffect>(Unit)

    fun setProfile(file: File) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                postSideEffect(EditProfileSideEffect.SuccessUpload(it.file))
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }
}