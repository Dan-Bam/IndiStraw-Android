package com.danbam.presentation.ui.profile.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.EditProfileParam
import com.danbam.domain.usecase.account.EditProfileUseCase
import com.danbam.domain.usecase.account.GetProfileUseCase
import com.danbam.domain.usecase.file.SendFileUseCase
import com.danbam.presentation.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileVieModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase,
) : ContainerHost<EditProfileState, EditProfileSideEffect>, ViewModel() {
    override val container = container<EditProfileState, EditProfileSideEffect>(EditProfileState())

    fun getProfile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce {
                    state.copy(
                        profileUrl = it.profileUrl,
                        name = it.name,
                        phoneNumber = it.phoneNumber,
                        address = it.address
                    )
                }
                postSideEffect(EditProfileSideEffect.GetProfile(it.name))
            }
        }
    }

    fun setProfileImage(file: File) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                postSideEffect(EditProfileSideEffect.SuccessUpload)
                reduce { state.copy(profileUrl = it.file) }
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun saveProfile(name: String) = intent {
        viewModelScope.launch {
            editProfileUseCase(
                EditProfileParam(
                    name = name,
                    profileUrl = state.profileUrl
                )
            )
        }
    }
}