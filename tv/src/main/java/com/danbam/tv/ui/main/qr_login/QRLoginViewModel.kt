package com.danbam.tv.ui.main.qr_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.qr_code.ConnectQRCodeUseCase
import com.danbam.domain.usecase.qr_code.GetQRCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QRLoginViewModel @Inject constructor(
    private val getQRCodeUseCase: GetQRCodeUseCase,
    private val connectQRCodeUseCase: ConnectQRCodeUseCase,
) : ContainerHost<QRLoginState, Unit>, ViewModel() {
    override val container = container<QRLoginState, Unit>(QRLoginState())

    fun getQRCode(onSuccess: () -> Unit) = intent {
        viewModelScope.launch {
            getQRCodeUseCase().onSuccess {
                reduce { state.copy(uuid = it) }
                connectQRCodeUseCase(uuid = it, onSuccess = onSuccess)
            }
        }
    }
}