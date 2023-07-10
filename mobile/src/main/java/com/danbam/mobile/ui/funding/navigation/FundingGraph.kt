package com.danbam.mobile.ui.funding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.mobile.ui.funding.all.FundingAllScreen
import com.danbam.mobile.ui.funding.detail.FundingDetailScreen
import com.danbam.mobile.ui.funding.make.MakeFundingScreen
import com.google.accompanist.navigation.animation.composable

sealed class FundingNavigationItem(val route: String) {
    object Make : FundingNavigationItem("fundingMake")
    object Detail : FundingNavigationItem("fundingDetail")
    object All : FundingNavigationItem("fundingAll")
}

object FundingDeepLinkKey {
    const val FUNDING_INDEX = "fundingIndex"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fundingGraph(navController: NavHostController) {
    composable(route = FundingNavigationItem.Make.route) {
        MakeFundingScreen(navController = navController)
    }
    composable(
        route = FundingNavigationItem.Detail.route
            + FundingDeepLinkKey.FUNDING_INDEX + "{${FundingDeepLinkKey.FUNDING_INDEX}}",
        arguments = listOf(
            navArgument(FundingDeepLinkKey.FUNDING_INDEX) {
                type = NavType.LongType
            }
        )
    ) {
        val fundingIndex = it.arguments?.getLong(FundingDeepLinkKey.FUNDING_INDEX) ?: 0L
        FundingDetailScreen(navController = navController, fundingIndex = fundingIndex)
    }
    composable(route = FundingNavigationItem.All.route) {
        FundingAllScreen(navController = navController)
    }
}