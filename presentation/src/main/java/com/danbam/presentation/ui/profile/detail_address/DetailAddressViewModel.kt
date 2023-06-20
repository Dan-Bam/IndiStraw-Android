package com.danbam.presentation.ui.profile.detail_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.ChangeAddressParam
import com.danbam.domain.usecase.account.ChangeAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailAddressViewModel @Inject constructor(
    private val changeAddressUseCase: ChangeAddressUseCase,
) : ContainerHost<Unit, DetailAddressSideEffect>, ViewModel() {
    override val container = container<Unit, DetailAddressSideEffect>(Unit)

    fun changeAddress(detailAddress: String, streetAddress: String, zipCode: String) = intent {
        viewModelScope.launch {
            changeAddressUseCase(
                ChangeAddressParam(
                    zipCode = zipCode,
                    streetAddress = streetAddress,
                    detailAddress = detailAddress
                )
            ).onSuccess {
                postSideEffect(DetailAddressSideEffect.SuccessChangeAddress)
            }
        }
    }
}