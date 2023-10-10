package com.danbam.mobile.ui.funding.pay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.param.FundingParam
import com.danbam.domain.usecase.account.GetProfileUseCase
import com.danbam.domain.usecase.funding.FundingUseCase
import com.danbam.domain.usecase.funding.GetReceiptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FundingRewardViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getReceiptUseCase: GetReceiptUseCase,
    private val fundingUseCase: FundingUseCase
) : ContainerHost<FundingRewardState, FundingRewardSideEffect>, ViewModel() {
    override val container =
        container<FundingRewardState, FundingRewardSideEffect>(FundingRewardState())

    fun getProfile() = intent {
        viewModelScope.launch {
            getProfileUseCase().onSuccess {
                reduce {
                    state.copy(
                        name = it.name,
                        phoneNumber = it.phoneNumber,
                        address = it.address,
                        zipCode = it.zipcode
                    )
                }
            }
        }
    }

    fun getReceipt() = intent {
        getReceiptUseCase().onSuccess {
            reduce {
                state.copy(receiptId = it)
            }
        }
    }

    fun funding(
        receiptId: String,
        crowdfundingIdx: Long,
        rewardIdx: Long,
        price: Long,
        extraPrice: Long
    ) = intent {
        fundingUseCase(
            crowdfundingIdx = crowdfundingIdx,
            rewardIdx = rewardIdx,
            fundingParam = FundingParam(
                receiptId = receiptId,
                price = price,
                extraPrice = extraPrice
            )
        ).onSuccess {
            postSideEffect(FundingRewardSideEffect.SuccessFunding)
        }
    }
}