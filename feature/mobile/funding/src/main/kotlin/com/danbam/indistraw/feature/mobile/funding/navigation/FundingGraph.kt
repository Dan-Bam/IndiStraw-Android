package com.danbam.indistraw.feature.mobile.funding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.feature.mobile.funding.all.FundingAllScreen
import com.danbam.indistraw.feature.mobile.funding.detail.FundingDetailScreen
import com.danbam.indistraw.feature.mobile.funding.make.MakeFundingScreen
import com.danbam.indistraw.feature.mobile.funding.my.MyFundingScreen
import com.danbam.indistraw.feature.mobile.funding.pay.FundingRewardScreen
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.funding.FundingNavigationItem
import com.google.accompanist.navigation.animation.composable

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
        val fundingIdx = it.arguments?.getLong(FundingDeepLinkKey.FUNDING_INDEX) ?: 0L
        FundingDetailScreen(navController = navController, fundingIdx = fundingIdx)
    }
    composable(route = FundingNavigationItem.All.route) {
        FundingAllScreen(navController = navController)
    }
    composable(
        route = FundingNavigationItem.FundingReward.route
            + FundingDeepLinkKey.FUNDING_INDEX + "{${FundingDeepLinkKey.FUNDING_INDEX}}"
            + FundingDeepLinkKey.REWARD_INDEX + "{${FundingDeepLinkKey.REWARD_INDEX}}"
            + FundingDeepLinkKey.REWARD_TITLE + "{${FundingDeepLinkKey.REWARD_TITLE}}"
            + FundingDeepLinkKey.REWARD_DESCRIPTION + "{${FundingDeepLinkKey.REWARD_DESCRIPTION}}"
            + FundingDeepLinkKey.REWARD_PRICE + "{${FundingDeepLinkKey.REWARD_PRICE}}",
        arguments = listOf(
            navArgument(FundingDeepLinkKey.FUNDING_INDEX) {
                type = NavType.LongType
            },
            navArgument(FundingDeepLinkKey.REWARD_INDEX) {
                type = NavType.LongType
            },
            navArgument(FundingDeepLinkKey.REWARD_TITLE) {
                type = NavType.StringType
            },
            navArgument(FundingDeepLinkKey.REWARD_DESCRIPTION) {
                type = NavType.StringType
            },
            navArgument(FundingDeepLinkKey.REWARD_PRICE) {
                type = NavType.LongType
            }
        )
    ) {
        val fundingIdx = it.arguments?.getLong(FundingDeepLinkKey.FUNDING_INDEX) ?: 0L
        val rewardIndex = it.arguments?.getLong(FundingDeepLinkKey.REWARD_INDEX) ?: 0L
        val rewardTitle = it.arguments?.getString(FundingDeepLinkKey.REWARD_TITLE) ?: ""
        val rewardDescription = it.arguments?.getString(FundingDeepLinkKey.REWARD_DESCRIPTION) ?: ""
        val rewardPrice = it.arguments?.getLong(FundingDeepLinkKey.REWARD_PRICE) ?: 0L
        FundingRewardScreen(
            navController = navController,
            fundingIdx = fundingIdx,
            rewardIndex = rewardIndex,
            rewardTitle = rewardTitle,
            rewardDescription = rewardDescription,
            rewardPrice = rewardPrice
        )
    }
    composable(route = FundingNavigationItem.MyDetail.route
        + FundingDeepLinkKey.FUNDING_INDEX + "{${FundingDeepLinkKey.FUNDING_INDEX}}",
        arguments = listOf(
            navArgument(FundingDeepLinkKey.FUNDING_INDEX) {
                type = NavType.LongType
            }
        )
    ) {
        val fundingIdx = it.arguments?.getLong(FundingDeepLinkKey.FUNDING_INDEX) ?: 0L
        MyFundingScreen(navController = navController, fundingIdx = fundingIdx)
    }
}