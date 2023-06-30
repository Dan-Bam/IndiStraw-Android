package com.danbam.mobile.ui.funding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.mobile.ui.funding.make.MakeFundingScreen
import com.google.accompanist.navigation.animation.composable

sealed class FundingNavigationItem(val route: String) {
    object MakeFunding : FundingNavigationItem("makeFunding")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fundingGraph(navController: NavHostController) {
    composable(route = FundingNavigationItem.MakeFunding.route) {
        MakeFundingScreen(navController = navController)
    }
}