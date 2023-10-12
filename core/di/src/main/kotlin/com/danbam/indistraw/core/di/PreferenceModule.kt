package com.danbam.indistraw.core.di

import com.danbam.indistraw.core.data.local.preference.AuthPreference
import com.danbam.indistraw.core.data.local.preference.AuthPreferenceImpl
import com.danbam.indistraw.core.data.local.preference.SystemPreference
import com.danbam.indistraw.core.data.local.preference.SystemPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun bindAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl,
    ): AuthPreference

    @Binds
    abstract fun bindSystemPreference(
        systemPreferenceImpl: SystemPreferenceImpl
    ): SystemPreference
}