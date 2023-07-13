package com.danbam.domain.repository

import androidx.paging.PagingData
import com.danbam.domain.entity.MovieEntity
import com.danbam.domain.param.MovieCreateParam
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun movieCreate(movieCreateParam: MovieCreateParam)
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieEntity>>
}