package com.danbam.indistraw.feature.mobile.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.ui.handling.errorHandling
import com.danbam.indistraw.core.design_system.util.internal.isId
import com.danbam.indistraw.core.design_system.util.internal.isPassword
import com.danbam.indistraw.core.domain.param.auth.SignUpParam
import com.danbam.indistraw.core.domain.usecase.auth.CheckIdUseCase
import com.danbam.indistraw.core.domain.usecase.auth.SignUpUseCase
import com.danbam.indistraw.core.domain.usecase.file.SendFileUseCase
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
class SignUpViewModel @Inject constructor(
    private val checkIdUseCase: CheckIdUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val sendFileUseCase: SendFileUseCase,
) : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {
    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun setName(name: String) = intent {
        if (name.isEmpty()) postSideEffect(SignUpSideEffect.EmptyNameException)
        else {
            reduce { state.copy(name = name) }
            postSideEffect(SignUpSideEffect.Next)
        }
    }

    fun setPhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }

    fun setProfile(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                reduce { state.copy(profileUrl = it.file) }
                onUploaded(it.file)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun setId(id: String) = intent {
        if (id.isEmpty()) postSideEffect(SignUpSideEffect.EmptyIdException)
        else if (!id.isId()) postSideEffect(SignUpSideEffect.MatchIdException)
        else {
            viewModelScope.launch {
                checkIdUseCase(id = id).onSuccess {
                    reduce { state.copy(id = id) }
                    postSideEffect(SignUpSideEffect.Next)
                }.onFailure {
                    it.errorHandling(unknownAction = {}, conflictException = {
                        postSideEffect(SignUpSideEffect.EnrollIdException)
                    })
                }
            }
        }
    }

    fun setPassword(password: String, rePassword: String, onNext: () -> Unit) = intent {
        if (password.isEmpty()) postSideEffect(SignUpSideEffect.EmptyPasswordException)
        else if (rePassword.isEmpty()) postSideEffect(SignUpSideEffect.EmptyRePasswordException)
        else if (password != rePassword) postSideEffect(SignUpSideEffect.DifferentPasswordException)
        else if (password.length !in (5..20)) postSideEffect(SignUpSideEffect.LengthPasswordException)
        else if (!password.isPassword()) postSideEffect(SignUpSideEffect.MatchPasswordException)
        else {
            reduce { state.copy(password = password) }
            onNext()
        }
    }

    fun signUp() = intent {
        viewModelScope.launch {
            signUpUseCase(
                SignUpParam(
                    id = state.id,
                    password = state.password,
                    name = state.name,
                    phoneNumber = state.phoneNumber,
                    profileUrl = state.profileUrl
                )
            ).onSuccess {
                postSideEffect(SignUpSideEffect.SuccessSignUp)
            }.onFailure {
                it.errorHandling(unknownAction = {
                    postSideEffect(SignUpSideEffect.FailSignUp)
                })
            }
        }
    }
}