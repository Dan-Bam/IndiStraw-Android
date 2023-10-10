package com.danbam.mobile.ui.auth.find.find_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.auth.ChangePasswordParam
import com.danbam.domain.usecase.account.ChangePasswordUseCase
import com.danbam.mobile.util.android.errorHandling
import com.danbam.mobile.util.parser.isPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
) : ContainerHost<Unit, FindPasswordSideEffect>, ViewModel() {
    override val container = container<Unit, FindPasswordSideEffect>(Unit)

    fun changePassword(phoneNumber: String, password: String, checkPassword: String) = intent {
        if (password.isEmpty()) postSideEffect(FindPasswordSideEffect.EmptyPasswordException)
        else if (checkPassword.isEmpty()) postSideEffect(FindPasswordSideEffect.EmptyRePasswordException)
        else if (password != checkPassword) postSideEffect(FindPasswordSideEffect.DifferentPasswordException)
        else if (password.length !in (8..20)) postSideEffect(FindPasswordSideEffect.LengthPasswordException)
        else if (!password.isPassword()) {
            postSideEffect(FindPasswordSideEffect.MatchPasswordException)
        } else {
            viewModelScope.launch {
                changePasswordUseCase(
                    changePasswordParam = ChangePasswordParam(
                        phoneNumber = phoneNumber,
                        password = password
                    )
                ).onSuccess {
                    postSideEffect(FindPasswordSideEffect.SuccessChange)
                }.onFailure {
                    it.errorHandling(
                        unknownAction = { postSideEffect(FindPasswordSideEffect.FailChangeException) })
                }
            }
        }
    }
}