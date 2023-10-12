package com.danbam.indistraw.mobile.ui.profile.qr_login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.usecase.qr_code.CheckQRCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class QRLoginViewModel @Inject constructor(
    private val checkQRCodeUseCase: CheckQRCodeUseCase,
) : ContainerHost<Unit, Unit>, ViewModel() {
    override val container = container<Unit, Unit>(Unit)

    fun checkQRCode(uuid: UUID) = intent {
        viewModelScope.launch {
            checkQRCodeUseCase(uuid = uuid).onSuccess {

            }
        }
    }
}