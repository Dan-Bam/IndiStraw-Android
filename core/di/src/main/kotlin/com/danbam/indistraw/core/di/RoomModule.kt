package com.danbam.indistraw.core.di

import android.content.Context
import androidx.room.Room
import com.danbam.indistraw.core.local.dao.RecentSearchDao
import com.danbam.indistraw.core.local.database.IndiStrawDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideIndiStrawDataBase(
        @ApplicationContext context: Context,
    ): com.danbam.indistraw.core.local.database.IndiStrawDataBase = Room
        .databaseBuilder(context, com.danbam.indistraw.core.local.database.IndiStrawDataBase::class.java, "IndiStrawDataBase")
        .build()

    @Provides
    fun provideRecentSearchDao(
        indiStrawDataBase: com.danbam.indistraw.core.local.database.IndiStrawDataBase,
    ): com.danbam.indistraw.core.local.dao.RecentSearchDao = indiStrawDataBase.resentSearchDao()
}