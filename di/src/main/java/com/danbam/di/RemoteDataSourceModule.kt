package com.danbam.di

import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl,
    ): AuthRemoteDataSource
}