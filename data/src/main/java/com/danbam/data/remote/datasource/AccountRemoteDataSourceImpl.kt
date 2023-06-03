package com.danbam.data.remote.datasource

import com.danbam.data.remote.api.AccountAPI
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : AccountRemoteDataSource {
}