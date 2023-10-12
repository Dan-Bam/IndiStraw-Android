package com.danbam.indistraw.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.danbam.indistraw.core.data.local.entity.search.RecentSearchEntity

@Dao
interface RecentSearchDao {
    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun search(recentSearchEntity: RecentSearchEntity)

    @Transaction
    @Query("SELECT * FROM recent_search")
    suspend fun getRecentSearch(): List<RecentSearchEntity>

    @Transaction
    @Delete
    suspend fun deleteRecentSearch(elements: List<RecentSearchEntity>)
}