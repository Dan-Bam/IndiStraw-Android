package com.danbam.indistraw.app.tv.ui.movie.movie

import androidx.paging.PagingData
import com.danbam.indistraw.core.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class MovieState(
    val currentMovieIndex: Long = 0,
    val movieAllPager: Flow<PagingData<MovieEntity>>? = null,
)