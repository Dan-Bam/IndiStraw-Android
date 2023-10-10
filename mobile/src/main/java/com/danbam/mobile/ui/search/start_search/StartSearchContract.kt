package com.danbam.mobile.ui.search.start_search

import com.danbam.domain.entity.search.RecentSearchEntity

data class StartSearchState(
    val recentSearchList: List<RecentSearchEntity> = listOf(),
    val popularTagList: List<String> = listOf()
)