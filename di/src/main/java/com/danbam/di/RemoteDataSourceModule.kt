package com.danbam.di

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.data.remote.datasource.AccountRemoteDataSourceImpl
import com.danbam.data.remote.datasource.AddressRemoteDataSource
import com.danbam.data.remote.datasource.AddressRemoteDataSourceImpl
import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSourceImpl
import com.danbam.data.remote.datasource.FileRemoteDataSource
import com.danbam.data.remote.datasource.FileRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl,
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindAccountRemoteDataSource(
        accountRemoteDataSourceImpl: AccountRemoteDataSourceImpl,
    ): AccountRemoteDataSource

    @Binds
    abstract fun bindFileRemoteDataSource(
        fileRemoteDataSourceImpl: FileRemoteDataSourceImpl,
    ): FileRemoteDataSource

    @Binds
    abstract fun bindAddressRemoteDataSource(
        addressRemoteDataSourceImpl: AddressRemoteDataSourceImpl,
    ): AddressRemoteDataSource
}