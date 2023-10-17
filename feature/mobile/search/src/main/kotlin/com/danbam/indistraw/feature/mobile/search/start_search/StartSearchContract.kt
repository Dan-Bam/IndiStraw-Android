package com.danbam.indistraw.feature.mobile.search.start_search

import com.danbam.indistraw.core.domain.entity.search.RecentSearchEntity

data class StartSearchState(
    val recentSearchList: List<RecentSearchEntity> = listOf(),
    val popularTagList: List<String> = listOf()
)