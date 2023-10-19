package com.danbam.indistraw.core.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danbam.indistraw.core.local.dao.RecentSearchDao
import com.danbam.indistraw.core.local.search.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1, exportSchema = false)
abstract class IndiStrawDataBase : RoomDatabase() {
    abstract fun resentSearchDao(): RecentSearchDao
}