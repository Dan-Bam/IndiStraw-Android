package com.danbam.indistraw.feature.mobile.navigation.movie

sealed class MovieNavigationItem(val route: String) {
    object Detail : MovieNavigationItem("movieDetail")
    object Play : MovieNavigationItem("moviePlay")
    object All : MovieNavigationItem("movieAll")
    object WriteIntroduce : MovieNavigationItem("movieWriteIntroduce")
    object AddActor : MovieNavigationItem("movieAddActor")
    object SearchActor : MovieNavigationItem("movieSearchActor")
    object WriteActor : MovieNavigationItem("movieWriteActor")
}