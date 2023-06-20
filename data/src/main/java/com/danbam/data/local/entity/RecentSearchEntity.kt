package com.danbam.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danbam.domain.entity.RecentSearchEntity as RecentSearchDomainEntity

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