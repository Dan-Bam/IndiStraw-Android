package com.danbam.indistraw.app.mobile.ui.funding.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.design_system.util.danbam.errorHandling
import com.danbam.indistraw.core.domain.usecase.crowd_funding.FundingDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FundingDetailViewModel @Inject constructor(
    private val fundingDetailUseCase: FundingDetailUseCase
) : ContainerHost<FundingDetailState, Unit>, ViewModel() {
    override val container = container<FundingDetailState, Unit>(FundingDetailState())

    fun getDetail(fundingIdx: Long) = intent {
        viewModelScope.launch {
            fundingDetailUseCase(fundingIdx = fundingIdx).onSuccess {
                reduce { state.copy(fundingDetailEntity = it) }
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }
}