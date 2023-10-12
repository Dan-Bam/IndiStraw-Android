package com.danbam.indistraw.di

import com.danbam.indistraw.data.repository.AccountRepositoryImpl
import com.danbam.indistraw.data.repository.AddressRepositoryImpl
import com.danbam.indistraw.data.repository.AuthRepositoryImpl
import com.danbam.indistraw.data.repository.BannerRepositoryImpl
import com.danbam.indistraw.data.repository.CrowdFundingRepositoryImpl
import com.danbam.indistraw.data.repository.FileRepositoryImpl
import com.danbam.indistraw.data.repository.FundingRepositoryImpl
import com.danbam.indistraw.data.repository.MovieRepositoryImpl
import com.danbam.indistraw.data.repository.QRCodeRepositoryImpl
import com.danbam.indistraw.data.repository.SearchRepositoryImpl
import com.danbam.indistraw.data.repository.SystemRepositoryImpl
import com.danbam.indistraw.domain.repository.AccountRepository
import com.danbam.indistraw.domain.repository.AddressRepository
import com.danbam.indistraw.domain.repository.AuthRepository
import com.danbam.indistraw.domain.repository.BannerRepository
import com.danbam.indistraw.domain.repository.CrowdFundingRepository
import com.danbam.indistraw.domain.repository.FileRepository
import com.danbam.indistraw.domain.repository.FundingRepository
import com.danbam.indistraw.domain.repository.MovieRepository
import com.danbam.indistraw.domain.repository.QRCodeRepository
import com.danbam.indistraw.domain.repository.SearchRepository
import com.danbam.indistraw.domain.repository.SystemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl,
    ): AccountRepository

    @Binds
    abstract fun bindFileRepository(
        fileRepositoryImpl: FileRepositoryImpl,
    ): FileRepository

    @Binds
    abstract fun bindAddressRepository(
        addressRepositoryImpl: AddressRepositoryImpl,
    ): AddressRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl,
    ): SearchRepository

    @Binds
    abstract fun bindCrowdFundingRepository(
        fundingRepositoryImpl: CrowdFundingRepositoryImpl,
    ): CrowdFundingRepository

    @Binds
    abstract fun bindQRCodeRepository(
        qrCodeRepositoryImpl: QRCodeRepositoryImpl,
    ): QRCodeRepository

    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    abstract fun bindFundingRepository(
        fundingRepositoryImpl: FundingRepositoryImpl
    ): FundingRepository

    @Binds
    abstract fun bindSystemRepository(
        systemRepositoryImpl: SystemRepositoryImpl
    ): SystemRepository

    @Binds
    abstract fun bindBannerRepository(
        bannerRepositoryImpl: BannerRepositoryImpl
    ): BannerRepository
}
