package com.danbam.indistraw.feature.mobile.search.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.indistraw.feature.mobile.navigation.search.SearchNavigationItem
import com.danbam.indistraw.feature.mobile.search.search.SearchScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    composable(route = SearchNavigationItem.Search.route) {
        SearchScreen(navController = navController)
    }
}