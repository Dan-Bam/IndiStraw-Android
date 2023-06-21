package com.danbam.mobile.ui.search.start_search

import com.danbam.domain.entity.RecentSearchEntity

data class StartSearchState(
    val recentSearchList: List<RecentSearchEntity?> = listOf(),
)