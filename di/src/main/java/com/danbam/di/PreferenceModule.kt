package com.danbam.di

import com.danbam.data.local.preference.AuthPreference
import com.danbam.data.local.preference.AuthPreferenceImpl
import com.danbam.data.local.preference.SystemPreference
import com.danbam.data.local.preference.SystemPreferenceImpl
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