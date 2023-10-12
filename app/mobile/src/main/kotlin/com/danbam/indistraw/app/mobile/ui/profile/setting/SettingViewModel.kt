package com.danbam.indistraw.app.mobile.ui.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.util.danbam.errorHandling
import com.danbam.indistraw.core.domain.usecase.account.WithdrawUseCase
import com.danbam.indistraw.core.domain.usecase.auth.ClearTokenUseCase
import com.danbam.indistraw.core.domain.usecase.auth.LogoutUseCase
import com.danbam.indistraw.core.domain.usecase.system.SaveLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val withdrawUseCase: WithdrawUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    private val saveLanguageUseCase: SaveLanguageUseCase,
) : ContainerHost<Unit, SettingSideEffect>, ViewModel() {
    override val container = container<Unit, SettingSideEffect>(Unit)

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

    fun saveLanguage(language: String) = intent {
        viewModelScope.launch {
            saveLanguageUseCase(language = language)
        }
    }
}