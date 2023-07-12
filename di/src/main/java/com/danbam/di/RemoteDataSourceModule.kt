package com.danbam.di

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.data.remote.datasource.AccountRemoteDataSourceImpl
import com.danbam.data.remote.datasource.AddressRemoteDataSource
import com.danbam.data.remote.datasource.AddressRemoteDataSourceImpl
import com.danbam.data.remote.datasource.AuthRemoteDataSource
import com.danbam.data.remote.datasource.AuthRemoteDataSourceImpl
import com.danbam.data.remote.datasource.FileRemoteDataSource
import com.danbam.data.remote.datasource.FileRemoteDataSourceImpl
import com.danbam.data.remote.datasource.SearchRemoteDataSource
import com.danbam.data.remote.datasource.SearchRemoteDataSourceImpl
import com.danbam.data.remote.datasource.CrowdFundingRemoteDataSource
import com.danbam.data.remote.datasource.CrowdFundingRemoteDataSourceImpl
import com.danbam.data.remote.datasource.MovieRemoteDataSource
import com.danbam.data.remote.datasource.MovieRemoteDataSourceImpl
import com.danbam.data.remote.datasource.FundingRemoteDataSource
import com.danbam.data.remote.datasource.FundingRemoteDataSourceImpl
import com.danbam.data.remote.datasource.QRCodeRemoteDataSource
import com.danbam.data.remote.datasource.QRCodeRemoteDataSourceImpl
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

    @Binds
    abstract fun bindCrowdFundingRemoteDataSource(
        fundingRemoteDataSourceImpl: CrowdFundingRemoteDataSourceImpl,
    ): CrowdFundingRemoteDataSource

    @Binds
    abstract fun bindSearchRemoteDataSource(
        searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl,
    ): SearchRemoteDataSource

    @Binds
    abstract fun bindQRCodeRemoteDataSource(
        qrCodeRemoteDataSourceImpl: QRCodeRemoteDataSourceImpl,
    ): QRCodeRemoteDataSource

    @Binds
    abstract fun bindMovieRemoteDataSource(
        movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    ): MovieRemoteDataSource

    @Binds
    abstract fun bindFundingRemoteDataSource(
        fundingRemoteDataSourceImpl: FundingRemoteDataSourceImpl
    ): FundingRemoteDataSource
}