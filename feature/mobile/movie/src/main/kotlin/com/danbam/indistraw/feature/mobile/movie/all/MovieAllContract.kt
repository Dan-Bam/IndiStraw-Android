package com.danbam.indistraw.feature.mobile.movie.all

import androidx.paging.PagingData
import com.danbam.indistraw.core.domain.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class MovieAllState(
    val movieAllPager: Flow<PagingData<MovieEntity>>? = null,
)