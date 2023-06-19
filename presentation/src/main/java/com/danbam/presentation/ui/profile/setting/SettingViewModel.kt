package com.danbam.presentation.ui.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.account.WithdrawUseCase
import com.danbam.domain.usecase.auth.ClearTokenUseCase
import com.danbam.domain.usecase.auth.LogoutUseCase
import com.danbam.presentation.util.android.errorHandling
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
}