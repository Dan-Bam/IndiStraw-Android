package com.danbam.presentation.ui.find.find_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.ChangePasswordParam
import com.danbam.domain.usecase.account.ChangePasswordUseCase
import com.danbam.presentation.util.errorHandling
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
        if (password.isEmpty() || checkPassword.isEmpty()) postSideEffect(FindPasswordSideEffect.EmptyException)
        else if (password != checkPassword) postSideEffect(FindPasswordSideEffect.DifferentException)
        else if (password.length !in (8..20)) postSideEffect(FindPasswordSideEffect.LengthException)
        else if (!"^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*?~])[0-9a-zA-Z!@#\$%^&*?~]+\$".toRegex()
                .matches(password)
        ) {
            postSideEffect(FindPasswordSideEffect.MatchException)
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
                    it.errorHandling(unknownAction = { postSideEffect(FindPasswordSideEffect.FailChangeException) })
                }
            }
        }
    }
}