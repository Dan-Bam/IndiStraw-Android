package com.danbam.mobile.ui.funding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.danbam.mobile.ui.funding.detail.FundingDetailScreen
import com.danbam.mobile.ui.funding.make.MakeFundingScreen
import com.google.accompanist.navigation.animation.composable

sealed class FundingNavigationItem(val route: String) {
    object FundingMake : FundingNavigationItem("makeFunding")
    object FundingDetail : FundingNavigationItem("detailFunding")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fundingGraph(navController: NavHostController) {
    composable(route = FundingNavigationItem.FundingMake.route) {
        MakeFundingScreen(navController = navController)
    }
    composable(route = FundingNavigationItem.FundingDetail.route) {
        FundingDetailScreen(navController = navController)
    }
}