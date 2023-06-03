package com.danbam.di

import com.danbam.data.repository.AccountRepositoryImpl
import com.danbam.data.repository.AuthRepositoryImpl
import com.danbam.domain.repository.AccountRepository
import com.danbam.domain.repository.AuthRepository
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
}
