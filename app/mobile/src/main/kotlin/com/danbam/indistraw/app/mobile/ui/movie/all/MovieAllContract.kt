package com.danbam.indistraw.app.mobile.ui.movie.all

import androidx.paging.PagingData
import com.danbam.indistraw.core.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class MovieAllState(
    val movieAllPager: Flow<PagingData<MovieEntity>>? = null,
)