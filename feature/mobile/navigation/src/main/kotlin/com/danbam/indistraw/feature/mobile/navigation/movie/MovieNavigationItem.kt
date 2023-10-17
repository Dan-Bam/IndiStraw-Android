package com.danbam.indistraw.feature.mobile.navigation.movie

sealed class MovieNavigationItem(val route: String) {
    object Detail : MovieNavigationItem("movieDetail")
    object Play : MovieNavigationItem("moviePlay")
    object All : MovieNavigationItem("movieAll")
    object WriteIntroduce : MovieNavigationItem("movieWriteIntroduce")
    object AddPeople : MovieNavigationItem("movieAddPeople")
    object SearchPeople : MovieNavigationItem("movieSearchPeople")
    object WritePeople : MovieNavigationItem("movieWritePeople")
}