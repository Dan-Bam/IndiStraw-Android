package com.danbam.indistraw.core.di

import com.danbam.indistraw.core.remote.datasource.AccountRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.AccountRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.AddressRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.AddressRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.AuthRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.AuthRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.BannerRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.BannerRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.CrowdFundingRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.CrowdFundingRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.FileRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.FileRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.FundingRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.FundingRemoteDataSourceImpl
import com.danbam.indistraw.core.remote.datasource.MovieRemoteDataSource
import com.danbam.indistraw.core.remote.datasource.MovieRemoteDataSourceImpl
import com.danbam.indistraw.core.data.remote.datasource.QRCodeRemoteDataSource
import com.danbam.indistraw.core.data.remote.datasource.QRCodeRemoteDataSourceImpl
import com.danbam.indistraw.core.data.remote.datasource.SearchRemoteDataSource
import com.danbam.indistraw.core.data.remote.datasource.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.AuthRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.AuthRemoteDataSource

    @Binds
    abstract fun bindAccountRemoteDataSource(
        accountRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.AccountRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.AccountRemoteDataSource

    @Binds
    abstract fun bindFileRemoteDataSource(
        fileRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.FileRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.FileRemoteDataSource

    @Binds
    abstract fun bindAddressRemoteDataSource(
        addressRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.AddressRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.AddressRemoteDataSource

    @Binds
    abstract fun bindCrowdFundingRemoteDataSource(
        fundingRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.CrowdFundingRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.CrowdFundingRemoteDataSource

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
        movieRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.MovieRemoteDataSourceImpl,
    ): com.danbam.indistraw.core.remote.datasource.MovieRemoteDataSource

    @Binds
    abstract fun bindFundingRemoteDataSource(
        fundingRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.FundingRemoteDataSourceImpl
    ): com.danbam.indistraw.core.remote.datasource.FundingRemoteDataSource

    @Binds
    abstract fun bindBannerRemoteDataSource(
        bannerRemoteDataSourceImpl: com.danbam.indistraw.core.remote.datasource.BannerRemoteDataSourceImpl
    ): com.danbam.indistraw.core.remote.datasource.BannerRemoteDataSource
}