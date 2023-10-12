package com.danbam.indistraw.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danbam.indistraw.data.local.dao.RecentSearchDao
import com.danbam.indistraw.data.local.entity.search.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1)
abstract class IndiStrawDataBase : RoomDatabase() {
    abstract fun resentSearchDao(): RecentSearchDao
}