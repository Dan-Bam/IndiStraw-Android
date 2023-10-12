package com.danbam.indistraw.app.mobile.ui.funding.my

import com.danbam.indistraw.core.entity.funding.MyFundingEntity


data class MyFundingState(
    val myFundingEntity: MyFundingEntity = MyFundingEntity(
        title = "",
        thumbnailUrl = "",
        amount = MyFundingEntity.AmountEntity(0L, 0L, 0.0),
        remainingDay = 0,
        fundingCount = 0L,
        reward = listOf(),
        orderList = listOf()
    )
)