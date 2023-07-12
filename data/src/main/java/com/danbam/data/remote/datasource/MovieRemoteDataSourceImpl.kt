package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.MovieAPI
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieAPI: MovieAPI
) : MovieRemoteDataSource {
}