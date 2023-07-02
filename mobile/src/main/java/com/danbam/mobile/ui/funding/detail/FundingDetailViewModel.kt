package com.danbam.mobile.ui.funding.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.funding.FundingDetailUseCase
import com.danbam.mobile.util.android.errorHandling
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

    fun getDetail(fundingIndex: Long) = intent {
        viewModelScope.launch {
            fundingDetailUseCase(fundingIndex = fundingIndex).onSuccess {
                reduce { state.copy(fundingDetailEntity = it) }
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }
}