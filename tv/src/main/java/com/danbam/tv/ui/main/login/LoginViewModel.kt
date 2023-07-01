package com.danbam.tv.ui.main.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.LoginParam
import com.danbam.domain.usecase.auth.LoginUseCase
import com.danbam.tv.util.android.errorHandling
import com.danbam.tv.util.parser.isId
import com.danbam.tv.util.parser.isPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ContainerHost<Unit, LoginSideEffect>, ViewModel() {
    override val container = container<Unit, LoginSideEffect>(Unit)

    fun login(id: String, password: String) = intent {
        if (id.isEmpty()) postSideEffect(LoginSideEffect.EmptyIdException)
        else if (password.isEmpty()) postSideEffect(LoginSideEffect.EmptyPasswordException)
        else if (!id.isId()) postSideEffect(LoginSideEffect.MatchIdException)
        else if (!password.isPassword()) postSideEffect(LoginSideEffect.MatchPasswordException)
        else {
            viewModelScope.launch {
                loginUseCase(LoginParam(id = id, password = password)).onSuccess {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }.onFailure {
                    it.errorHandling(
                        unknownAction = {},
                        wrongDataException = { postSideEffect(LoginSideEffect.MatchPasswordException) },
                        notFoundException = { postSideEffect(LoginSideEffect.MatchIdException) }
                    )
                }
            }
        }
    }
}