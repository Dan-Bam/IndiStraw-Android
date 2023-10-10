package com.danbam.data.local.entity.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danbam.domain.entity.search.RecentSearchEntity as RecentSearchDomainEntity

@Entity(tableName = "recent_search")
data class RecentSearchEntity(
    @PrimaryKey val search: String,
)

fun RecentSearchEntity.toDomain() = RecentSearchDomainEntity(
    search = search
)

fun RecentSearchDomainEntity.toDB() = RecentSearchEntity(
    search = search
)