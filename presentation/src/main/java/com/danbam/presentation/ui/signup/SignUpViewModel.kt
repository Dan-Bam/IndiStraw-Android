package com.danbam.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.auth.CheckIdUseCase
import com.danbam.presentation.util.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val checkIdUseCase: CheckIdUseCase,
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

    fun setId(id: String) = intent {
        if (id.isEmpty()) postSideEffect(SignUpSideEffect.EmptyIdException)
        else if (id.length !in (6..15)) postSideEffect(SignUpSideEffect.MatchIdException)
        else {
            viewModelScope.launch {
                checkIdUseCase(id = id).onFailure {
                    it.errorHandling(unknownAction = {}, conflictException = {
                        postSideEffect(SignUpSideEffect.EnrollIdException)
                    }, noContentException = {
                        postSideEffect(SignUpSideEffect.Next)
                    })
                }
            }
        }
    }
}