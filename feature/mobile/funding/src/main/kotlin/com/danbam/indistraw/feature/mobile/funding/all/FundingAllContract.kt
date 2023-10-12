package com.danbam.indistraw.feature.mobile.funding.all

import androidx.paging.PagingData
import com.danbam.indistraw.core.entity.funding.FundingEntity
import kotlinx.coroutines.flow.Flow

data class FundingAllState(
    val fundingList: Flow<PagingData<FundingEntity>>? = null
)