package com.danbam.indistraw.mobile.ui.search.start_search

import com.danbam.indistraw.core.entity.search.RecentSearchEntity

data class StartSearchState(
    val recentSearchList: List<RecentSearchEntity> = listOf(),
    val popularTagList: List<String> = listOf()
)