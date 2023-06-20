package com.danbam.presentation.ui.profile.find_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.address.GetAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindAddressViewModel @Inject constructor(
    private val getAddressUseCase: GetAddressUseCase,
) : ContainerHost<FindAddressState, FindAddressSideEffect>, ViewModel() {
    override val container = container<FindAddressState, FindAddressSideEffect>(FindAddressState())

    fun getAddress(keyword: String) = intent {
        viewModelScope.launch {
            getAddressUseCase(keyword = keyword).onSuccess {
                reduce { state.copy(findAddressPager = it) }
            }
        }
    }
}