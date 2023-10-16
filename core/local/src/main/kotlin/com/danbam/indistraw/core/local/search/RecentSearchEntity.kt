package com.danbam.indistraw.core.local.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danbam.indistraw.core.domain.entity.search.RecentSearchEntity as RecentSearchDomainEntity

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