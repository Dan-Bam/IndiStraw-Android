package com.danbam.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.danbam.data.local.entity.RecentSearchEntity

@Dao
interface RecentSearchDao {
    @Insert(onConflict = REPLACE)
    suspend fun search(recentSearchEntity: RecentSearchEntity)

    @Query("SELECT * FROM recent_search")
    suspend fun getRecentSearch(): List<RecentSearchEntity?>
}