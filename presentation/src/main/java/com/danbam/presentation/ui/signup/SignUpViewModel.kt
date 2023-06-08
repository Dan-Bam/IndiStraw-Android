package com.danbam.presentation.ui.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {
    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun setName(name: String) = intent {
        if (name.isEmpty()) postSideEffect(SignUpSideEffect.EmptyNameException)
        else {
            reduce { state.copy(name = name) }
            postSideEffect(SignUpSideEffect.Next)
        }
    }
}