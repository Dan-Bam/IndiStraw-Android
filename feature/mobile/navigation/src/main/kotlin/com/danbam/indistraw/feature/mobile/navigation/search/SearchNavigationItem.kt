package com.danbam.indistraw.feature.mobile.navigation.search

sealed class SearchNavigationItem(val route: String) {
    object Search : SearchNavigationItem("search")
}