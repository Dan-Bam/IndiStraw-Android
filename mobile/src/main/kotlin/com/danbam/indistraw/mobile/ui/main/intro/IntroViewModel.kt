package com.danbam.indistraw.mobile.ui.main.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.util.danbam.errorHandling
import com.danbam.indistraw.core.domain.usecase.auth.IsLoginUseCase
import com.danbam.indistraw.core.domain.usecase.system.FetchLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val isLoginUseCase: IsLoginUseCase,
    private val fetchLanguageUseCase: FetchLanguageUseCase
) : ContainerHost<IntroState, IntroSideEffect>, ViewModel() {
    override val container = container<IntroState, IntroSideEffect>(IntroState())

    fun isLogin() = intent {
        viewModelScope.launch {
            isLoginUseCase().onSuccess {
                postSideEffect(IntroSideEffect.SuccessLogin)
            }.onFailure {
                it.errorHandling(
                    unknownAction = { reduce { state.copy(isNeedLogin = true) } },
                    expiredTokenException = { reduce { state.copy(isNeedLogin = true) } }
                )
            }
        }
    }

    fun fetchLanguage() = intent {
        viewModelScope.launch {
            fetchLanguageUseCase().onSuccess {
                reduce { state.copy(systemLanguage = it.ifEmpty { null }) }
            }
        }
    }
}