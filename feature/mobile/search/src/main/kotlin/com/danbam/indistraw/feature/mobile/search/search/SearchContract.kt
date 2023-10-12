package com.danbam.indistraw.feature.mobile.search.search

sealed class SearchType {
    object Start : SearchType()
    object Searching : SearchType()
    object Result : SearchType()
}

data class SearchState(
    val searchType: SearchType = SearchType.Start,
    val keyword: String = "",
)