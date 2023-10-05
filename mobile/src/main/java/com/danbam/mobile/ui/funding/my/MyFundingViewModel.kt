package com.danbam.mobile.ui.funding.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.usecase.crowd_funding.FundingMyDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyFundingViewModel @Inject constructor(
    private val fundingMyDetailUseCase: FundingMyDetailUseCase
) : ContainerHost<MyFundingState, Unit>, ViewModel() {
    override val container = container<MyFundingState, Unit>(MyFundingState())

    fun myDetail(crowdFundingIdx: Long) = intent {
        viewModelScope.launch {
            fundingMyDetailUseCase(crowdFundingIdx = crowdFundingIdx).onSuccess {
                reduce { state.copy(myFundingEntity = it) }
            }
        }
    }
}