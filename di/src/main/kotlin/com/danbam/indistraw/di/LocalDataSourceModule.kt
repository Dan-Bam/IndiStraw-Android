package com.danbam.indistraw.di

import com.danbam.indistraw.data.local.datasource.AuthLocalDataSource
import com.danbam.indistraw.data.local.datasource.AuthLocalDataSourceImpl
import com.danbam.indistraw.data.local.datasource.SearchLocalDataSource
import com.danbam.indistraw.data.local.datasource.SearchLocalDataSourceImpl
import com.danbam.indistraw.data.local.datasource.SystemLocalDataSource
import com.danbam.indistraw.data.local.datasource.SystemLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindAuthLocalDataSource(
        authLocalDataSourceImpl: AuthLocalDataSourceImpl,
    ): AuthLocalDataSource

    @Binds
    abstract fun bindSearchLocalDataSource(
        searchLocalDataSourceImpl: SearchLocalDataSourceImpl,
    ): SearchLocalDataSource

    @Binds
    abstract fun bindSystemLocalDataSource(
        systemLocalDataSourceImpl: SystemLocalDataSourceImpl
    ): SystemLocalDataSource
}