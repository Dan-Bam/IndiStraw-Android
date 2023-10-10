package com.danbam.mobile.ui.movie.all

import androidx.paging.PagingData
import com.danbam.domain.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class MovieAllState(
    val movieAllPager: Flow<PagingData<MovieEntity>>? = null,
)