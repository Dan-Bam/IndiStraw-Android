package com.danbam.indistraw.app.tv.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.util.android.Language
import com.danbam.indistraw.core.design_system.util.danbam.errorHandling
import com.danbam.indistraw.core.domain.usecase.account.GetProfileUseCase
import com.danbam.indistraw.core.domain.usecase.account.WithdrawUseCase
import com.danbam.indistraw.core.domain.usecase.auth.ClearTokenUseCase
import com.danbam.indistraw.core.domain.usecase.auth.LogoutUseCase
import com.danbam.indistraw.core.domain.usecase.system.FetchLanguageUseCase
import com.danbam.indistraw.core.domain.usecase.system.SaveLanguageUseCase
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
    private val getProfileUseCase: GetProfileUseCase,
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

    fun profile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce { state.copy(profileEntity = it) }
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