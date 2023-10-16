package com.danbam.indistraw.core.di

import com.danbam.indistraw.core.local.datasource.AuthLocalDataSource
import com.danbam.indistraw.core.local.datasource.AuthLocalDataSourceImpl
import com.danbam.indistraw.core.local.datasource.SearchLocalDataSource
import com.danbam.indistraw.core.local.datasource.SearchLocalDataSourceImpl
import com.danbam.indistraw.core.local.datasource.SystemLocalDataSource
import com.danbam.indistraw.core.local.datasource.SystemLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindAuthLocalDataSource(
        authLocalDataSourceImpl: com.danbam.indistraw.core.local.datasource.AuthLocalDataSourceImpl,
    ): com.danbam.indistraw.core.local.datasource.AuthLocalDataSource

    @Binds
    abstract fun bindSearchLocalDataSource(
        searchLocalDataSourceImpl: com.danbam.indistraw.core.local.datasource.SearchLocalDataSourceImpl,
    ): com.danbam.indistraw.core.local.datasource.SearchLocalDataSource

    @Binds
    abstract fun bindSystemLocalDataSource(
        systemLocalDataSourceImpl: com.danbam.indistraw.core.local.datasource.SystemLocalDataSourceImpl
    ): com.danbam.indistraw.core.local.datasource.SystemLocalDataSource
}