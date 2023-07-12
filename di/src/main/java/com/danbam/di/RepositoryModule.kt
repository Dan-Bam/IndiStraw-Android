package com.danbam.di

import com.danbam.data.repository.AccountRepositoryImpl
import com.danbam.data.repository.AddressRepositoryImpl
import com.danbam.data.repository.AuthRepositoryImpl
import com.danbam.data.repository.FileRepositoryImpl
import com.danbam.data.repository.CrowdFundingRepositoryImpl
import com.danbam.data.repository.MovieRepositoryImpl
import com.danbam.data.repository.QRCodeRepositoryImpl
import com.danbam.data.repository.SearchRepositoryImpl
import com.danbam.domain.repository.AccountRepository
import com.danbam.domain.repository.AddressRepository
import com.danbam.domain.repository.AuthRepository
import com.danbam.domain.repository.FileRepository
import com.danbam.domain.repository.CrowdFundingRepository
import com.danbam.domain.repository.MovieRepository
import com.danbam.domain.repository.QRCodeRepository
import com.danbam.domain.repository.SearchRepository
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
}
