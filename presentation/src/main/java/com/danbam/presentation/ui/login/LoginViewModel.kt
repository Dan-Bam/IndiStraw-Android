package com.danbam.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.LoginParam
import com.danbam.domain.usecase.auth.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ContainerHost<Unit, LoginSideEffect>, ViewModel() {
    override val container = container<Unit, LoginSideEffect>(Unit)

    fun login(id: String, password: String) = intent {
        if (id.isEmpty()) postSideEffect(LoginSideEffect.IdEmpty)
        else if (password.isEmpty()) postSideEffect(LoginSideEffect.PasswordEmpty)
        else if (id.length !in (6..15)) postSideEffect(LoginSideEffect.WrongId)
        else if (password.length !in (8..20) || !"^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^&*?~])[0-9a-zA-Z!@#\$%^&*?~]+\$".toRegex()
                .matches(password)
        ) postSideEffect(LoginSideEffect.WrongPassword)
        else {
            viewModelScope.launch {
                loginUseCase(LoginParam(id = id, password = password)).onSuccess {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }.onFailure {
                    it.errorHandling(
                        unknownAction = {},
                        wrongDataException = { postSideEffect(LoginSideEffect.WrongPassword) },
                        notFoundException = { postSideEffect(LoginSideEffect.WrongId) }
                    )
                }
            }
        }
    }
}