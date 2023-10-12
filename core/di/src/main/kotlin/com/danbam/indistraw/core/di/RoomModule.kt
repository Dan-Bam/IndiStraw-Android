package com.danbam.indistraw.core.di

import android.content.Context
import androidx.room.Room
import com.danbam.indistraw.core.data.local.dao.RecentSearchDao
import com.danbam.indistraw.core.data.local.database.IndiStrawDataBase
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
    ): IndiStrawDataBase = Room
        .databaseBuilder(context, IndiStrawDataBase::class.java, "IndiStrawDataBase")
        .build()

    @Provides
    fun provideRecentSearchDao(
        indiStrawDataBase: IndiStrawDataBase,
    ): RecentSearchDao = indiStrawDataBase.resentSearchDao()
}