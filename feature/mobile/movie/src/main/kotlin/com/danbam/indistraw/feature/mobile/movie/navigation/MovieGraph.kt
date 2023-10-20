package com.danbam.indistraw.feature.mobile.movie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danbam.indistraw.feature.mobile.movie.all.MovieAllScreen
import com.danbam.indistraw.feature.mobile.movie.detail.MovieDetailScreen
import com.danbam.indistraw.feature.mobile.movie.make.AddPeopleScreen
import com.danbam.indistraw.feature.mobile.movie.make.SearchPeopleScreen
import com.danbam.indistraw.feature.mobile.movie.make.WritePeopleScreen
import com.danbam.indistraw.feature.mobile.movie.make.WriteIntroduceScreen
import com.danbam.indistraw.feature.mobile.movie.play.MoviePlayScreen
import com.danbam.indistraw.feature.mobile.navigation.movie.PeopleType
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieDeepLinkKey
import com.danbam.indistraw.feature.mobile.navigation.movie.MovieNavigationItem
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.movieGraph(
    navController: NavHostController,
) {
    composable(
        route = MovieNavigationItem.Detail.route
            + MovieDeepLinkKey.MOVIE_INDEX + "{${MovieDeepLinkKey.MOVIE_INDEX}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            }
        )
    ) {
        val movieIdx = it.arguments?.getLong(MovieDeepLinkKey.MOVIE_INDEX) ?: 0
        MovieDetailScreen(navController = navController, movieIdx = movieIdx)
    }
    composable(
        route = MovieNavigationItem.Play.route
            + MovieDeepLinkKey.MOVIE_NAME + "{${MovieDeepLinkKey.MOVIE_NAME}}"
            + MovieDeepLinkKey.MOVIE_INDEX + "{${MovieDeepLinkKey.MOVIE_INDEX}}"
            + MovieDeepLinkKey.MOVIE_URL + "{${MovieDeepLinkKey.MOVIE_URL}}"
            + MovieDeepLinkKey.MOVIE_POSITION + "{${MovieDeepLinkKey.MOVIE_POSITION}}"
            + MovieDeepLinkKey.IS_VERTICAL + "{${MovieDeepLinkKey.IS_VERTICAL}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.MOVIE_NAME) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.MOVIE_INDEX) {
                type = NavType.LongType
            },
            navArgument(MovieDeepLinkKey.MOVIE_URL) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.MOVIE_POSITION) {
                type = NavType.FloatType
            },
            navArgument(MovieDeepLinkKey.IS_VERTICAL) {
                type = NavType.BoolType
            }
        )
    ) {
        val movieName = it.arguments?.getString(MovieDeepLinkKey.MOVIE_NAME) ?: ""
        val movieUrl = it.arguments?.getString(MovieDeepLinkKey.MOVIE_URL) ?: ""
        val movieIdx = it.arguments?.getLong(MovieDeepLinkKey.MOVIE_INDEX) ?: 0
        val moviePosition = it.arguments?.getFloat(MovieDeepLinkKey.MOVIE_POSITION) ?: 0F
        val isVertical = it.arguments?.getBoolean(MovieDeepLinkKey.IS_VERTICAL) ?: false
        MoviePlayScreen(
            movieName = movieName,
            movieUrl = movieUrl,
            movieIdx = movieIdx,
            position = moviePosition,
            isVertical = isVertical,
            navController = navController
        )
    }
    composable(route = MovieNavigationItem.All.route) {
        MovieAllScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.WriteIntroduce.route) {
        WriteIntroduceScreen(navController = navController)
    }
    composable(route = MovieNavigationItem.AddPeople.route) {
        AddPeopleScreen(navController = navController)
    }
    composable(
        route = MovieNavigationItem.WritePeople.route
            + MovieDeepLinkKey.PEOPLE_TYPE + "{${MovieDeepLinkKey.PEOPLE_TYPE}}"
            + MovieDeepLinkKey.IS_ENROLL + "{${MovieDeepLinkKey.IS_ENROLL}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.PEOPLE_TYPE) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.IS_ENROLL) {
                type = NavType.BoolType
            }
        )
    ) {
        val peopleType =
            it.arguments?.getString(MovieDeepLinkKey.PEOPLE_TYPE) ?: PeopleType.ACTOR.route
        val isEnroll = it.arguments?.getBoolean(MovieDeepLinkKey.IS_ENROLL) ?: false
        WritePeopleScreen(
            navController = navController,
            peopleType = peopleType,
            isEnroll = isEnroll
        )
    }
    composable(
        route = MovieNavigationItem.SearchPeople.route
            + MovieDeepLinkKey.PEOPLE_TYPE + "{${MovieDeepLinkKey.PEOPLE_TYPE}}"
            + MovieDeepLinkKey.IS_ENROLL + "{${MovieDeepLinkKey.IS_ENROLL}}",
        arguments = listOf(
            navArgument(MovieDeepLinkKey.PEOPLE_TYPE) {
                type = NavType.StringType
            },
            navArgument(MovieDeepLinkKey.IS_ENROLL) {
                type = NavType.BoolType
            }
        )
    ) {
        val addPeopleType =
            it.arguments?.getString(MovieDeepLinkKey.PEOPLE_TYPE) ?: PeopleType.ACTOR.route
        val isEnroll = it.arguments?.getBoolean(MovieDeepLinkKey.IS_ENROLL) ?: false
        SearchPeopleScreen(
            navController = navController,
            peopleType = addPeopleType,
            isEnroll = isEnroll
        )
    }
}