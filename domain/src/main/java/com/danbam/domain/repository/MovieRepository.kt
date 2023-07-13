package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieEntity>>
}