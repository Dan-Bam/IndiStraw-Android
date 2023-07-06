package com.danbam.mobile.ui.funding.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.funding.FundingAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FundingAllViewModel @Inject constructor(
    private val fundingAllUseCase: FundingAllUseCase
) : ContainerHost<FundingAllState, Unit>, ViewModel() {
    override val container = container<FundingAllState, Unit>(FundingAllState())

    fun fundingList() = intent {
        viewModelScope.launch {
            fundingAllUseCase().onSuccess {
                reduce {
                    state.copy(
                        fundingList = it
                    )
                }
            }
        }
    }
}