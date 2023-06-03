package com.danbam.data.repository

import com.danbam.data.remote.datasource.AccountRemoteDataSource
import com.danbam.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
) : AccountRepository {
}