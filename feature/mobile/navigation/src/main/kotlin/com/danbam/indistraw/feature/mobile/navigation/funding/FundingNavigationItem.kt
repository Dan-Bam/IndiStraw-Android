package com.danbam.indistraw.feature.mobile.navigation.funding

sealed class FundingNavigationItem(val route: String) {
    object Make : FundingNavigationItem("fundingMake")
    object Detail : FundingNavigationItem("fundingDetail")
    object All : FundingNavigationItem("fundingAll")
    object FundingReward : FundingNavigationItem("fundingReward")
    object MyDetail : FundingNavigationItem("fundingMyDetail")
}