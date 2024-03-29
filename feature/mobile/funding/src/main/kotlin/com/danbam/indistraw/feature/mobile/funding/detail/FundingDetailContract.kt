package com.danbam.indistraw.feature.mobile.funding.detail

import com.danbam.indistraw.core.domain.entity.funding.FundingDetailEntity
import java.util.UUID

data class FundingDetailState(
    val fundingDetailEntity: FundingDetailEntity = FundingDetailEntity(
        title = "",
        description = "",
        writer = FundingDetailEntity.WriterEntity(UUID.randomUUID(), ""),
        amount = FundingDetailEntity.AmountEntity(0, 0, 0.0),
        remainingDay = 0,
        fundingCount = 0,
        reward = listOf(),
        status = "",
        thumbnailUrl = "",
        imageList = listOf(),
        fileList = listOf(),
        isFunding = false
    )
)