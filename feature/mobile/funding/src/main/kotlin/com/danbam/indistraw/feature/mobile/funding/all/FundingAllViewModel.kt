package com.danbam.indistraw.feature.mobile.funding.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.domain.usecase.crowd_funding.FundingAllUseCase
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
) : ContainerHost<com.danbam.indistraw.feature.mobile.funding.all.FundingAllState, Unit>, ViewModel() {
    override val container = container<com.danbam.indistraw.feature.mobile.funding.all.FundingAllState, Unit>(
        com.danbam.indistraw.feature.mobile.funding.all.FundingAllState()
    )

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