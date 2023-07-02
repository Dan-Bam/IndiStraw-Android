package com.danbam.mobile.ui.funding.detail

import com.danbam.domain.entity.FundingDetailEntity
import java.util.UUID

data class FundingDetailState(
    val fundingDetailEntity: FundingDetailEntity = FundingDetailEntity(
        title = "",
        description = "",
        writer = FundingDetailEntity.WriterEntity(UUID.randomUUID(), ""),
        amount = FundingDetailEntity.AmountEntity(0F, 0F, 0),
        remainingDay = 0,
        fundingCount = 0,
        reward = listOf(),
        status = "",
        thumbnailUrl = "",
        imageList = listOf(),
        fileList = listOf()
    )
)