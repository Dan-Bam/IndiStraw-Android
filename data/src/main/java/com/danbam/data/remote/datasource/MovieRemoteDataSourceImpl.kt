package com.danbam.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danbam.data.remote.api.MovieAPI
import com.danbam.data.remote.pagingsource.MoviePagingSource
import com.danbam.data.remote.request.MovieCreateRequest
import com.danbam.data.remote.request.MoviePeopleRequest
import com.danbam.data.remote.response.MoviePeopleResponse
import com.danbam.data.remote.response.MovieResponse
import com.danbam.data.remote.util.errorHandling
import com.danbam.data.remote.util.indiStrawApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieAPI: MovieAPI
) : MovieRemoteDataSource {
    override suspend fun movieCreate(movieCreateRequest: MovieCreateRequest) = indiStrawApiCall {
        movieAPI.movieCreate(movieCreateRequest = movieCreateRequest).errorHandling()
    }

    override suspend fun movieList(genre: String?): Flow<PagingData<MovieResponse>> =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    movieAPI = movieAPI,
                    genre = genre
                )
            }).flow

    override suspend fun searchMoviePeople(
        actorType: String,
        name: String
    ): List<MoviePeopleResponse> = indiStrawApiCall {
        movieAPI.searchMoviePeople(actorType = actorType, name = name)
    }

    override suspend fun addMoviePeople(actorType: String, moviePeopleRequest: MoviePeopleRequest) =
        indiStrawApiCall {
            movieAPI.addMoviePeople(actorType = actorType, moviePeopleRequest = moviePeopleRequest)
        }
}