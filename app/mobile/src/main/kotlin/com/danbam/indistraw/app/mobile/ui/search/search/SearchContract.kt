package com.danbam.indistraw.app.mobile.ui.search.search

sealed class SearchType {
    object Start : SearchType()
    object Searching : SearchType()
    object Result : SearchType()
}

data class SearchState(
    val searchType: SearchType = SearchType.Start,
    val keyword: String = "",
)