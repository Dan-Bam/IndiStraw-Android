package com.danbam.tv.ui.main.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.auth.IsLoginUseCase
import com.danbam.domain.usecase.system.FetchLanguageUseCase
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

    private fun isLogin() = intent {
        viewModelScope.launch {
            isLoginUseCase().onSuccess {
                postSideEffect(IntroSideEffect.LoginSuccess)
            }.onFailure {
                postSideEffect(IntroSideEffect.LoginFail)
            }
        }
    }

    fun fetchLanguage() = intent {
        viewModelScope.launch {
            fetchLanguageUseCase().onSuccess { language ->
                reduce { state.copy(currentLanguage = language) }
                isLogin()
            }.onFailure {
                isLogin()
            }
        }
    }
}