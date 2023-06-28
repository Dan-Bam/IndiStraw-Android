package com.danbam.mobile.ui.search.searching

import androidx.paging.PagingData
import com.danbam.domain.entity.RelatedSearchEntity
import kotlinx.coroutines.flow.Flow

data class SearchingState(
    val relatedSearchPager: Flow<PagingData<RelatedSearchEntity>>? = null
)