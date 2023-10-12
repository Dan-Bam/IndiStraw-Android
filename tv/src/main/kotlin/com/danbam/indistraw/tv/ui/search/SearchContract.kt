package com.danbam.indistraw.tv.ui.search

import androidx.paging.PagingData
import com.danbam.indistraw.domain.entity.movie.MovieEntity
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val relatedSearchList: List<String> = listOf(),
    val moviePopularList: List<MovieEntity> = listOf(),
    val resultPager: Flow<PagingData<MovieEntity>>? = null
)