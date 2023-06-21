package com.danbam.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danbam.data.local.dao.RecentSearchDao
import com.danbam.data.local.entity.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1)
abstract class IndiStrawDataBase : RoomDatabase() {
    abstract fun resentSearchDao(): RecentSearchDao
}