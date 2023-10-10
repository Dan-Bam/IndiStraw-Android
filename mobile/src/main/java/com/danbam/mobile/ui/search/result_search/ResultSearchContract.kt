package com.danbam.mobile.ui.search.result_search

import androidx.paging.PagingData
import com.danbam.domain.entity.funding.FundingEntity
import com.danbam.domain.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class ResultSearchState(
    val moviePager: Flow<PagingData<MovieEntity>>? = null,
    val fundingPager: Flow<PagingData<FundingEntity>>? = null
)