package com.danbam.data.remote.datasource

import androidx.paging.PagingData
import com.danbam.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun movieList(genre: String? = null): Flow<PagingData<MovieResponse>>
}