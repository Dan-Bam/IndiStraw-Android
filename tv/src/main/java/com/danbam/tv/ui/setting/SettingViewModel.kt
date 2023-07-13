package com.danbam.tv.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.design_system.util.Language
import com.danbam.domain.usecase.account.WithdrawUseCase
import com.danbam.domain.usecase.auth.ClearTokenUseCase
import com.danbam.domain.usecase.auth.LogoutUseCase
import com.danbam.domain.usecase.system.FetchLanguageUseCase
import com.danbam.domain.usecase.system.SaveLanguageUseCase
import com.danbam.tv.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val withdrawUseCase: WithdrawUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val fetchLanguageUseCase: FetchLanguageUseCase
) : ContainerHost<SettingState, SettingSideEffect>, ViewModel() {
    override val container = container<SettingState, SettingSideEffect>(SettingState())

    fun logout() = intent {
        viewModelScope.launch {
            logoutUseCase().onSuccess {
                postSideEffect(SettingSideEffect.SuccessLogout)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun withdraw() = intent {
        viewModelScope.launch {
            runCatching {
                withdrawUseCase()
                clearTokenUseCase()
            }.onSuccess {
                postSideEffect(SettingSideEffect.SuccessWithdraw)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun saveLanguage(language: Language) = intent {
        viewModelScope.launch {
            saveLanguageUseCase(language = language.type).onSuccess {
                reduce { state.copy(currentLanguage = language) }
            }
        }
    }

    fun fetchLanguage() = intent {
        viewModelScope.launch {
            fetchLanguageUseCase().onSuccess { language ->
                Language.toList().forEach {
                    if (it.type == language) reduce { state.copy(currentLanguage = it) }
                }
            }
        }
    }
}