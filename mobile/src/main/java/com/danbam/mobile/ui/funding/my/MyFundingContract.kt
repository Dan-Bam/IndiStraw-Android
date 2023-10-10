package com.danbam.mobile.ui.funding.my

import com.danbam.domain.entity.funding.MyFundingEntity

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