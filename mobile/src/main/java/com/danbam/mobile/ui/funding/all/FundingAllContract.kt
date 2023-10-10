package com.danbam.mobile.ui.funding.all

import androidx.paging.PagingData
import com.danbam.domain.entity.FundingEntity
import kotlinx.coroutines.flow.Flow

data class FundingAllState(
    val fundingList: Flow<PagingData<FundingEntity>>? = null
)