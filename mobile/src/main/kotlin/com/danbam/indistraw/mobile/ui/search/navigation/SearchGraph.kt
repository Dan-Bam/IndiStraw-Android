package com.danbam.indistraw.mobile.ui.search.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.indistraw.mobile.ui.search.search.SearchScreen
import com.google.accompanist.navigation.animation.composable

sealed class SearchNavigationItem(val route: String) {
    object Search : SearchNavigationItem("search")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    composable(route = SearchNavigationItem.Search.route) {
        SearchScreen(navController = navController)
    }
}