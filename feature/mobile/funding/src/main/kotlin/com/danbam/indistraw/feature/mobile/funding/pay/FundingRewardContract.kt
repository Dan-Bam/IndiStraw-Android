package com.danbam.indistraw.feature.mobile.funding.pay

data class FundingRewardState(
    val name: String = "",
    val phoneNumber: String = "",
    val zipCode: String? = null,
    val address: String? = null,
    val receiptId: String = "",
)

sealed class FundingRewardSideEffect {
    object SuccessFunding : FundingRewardSideEffect()
}