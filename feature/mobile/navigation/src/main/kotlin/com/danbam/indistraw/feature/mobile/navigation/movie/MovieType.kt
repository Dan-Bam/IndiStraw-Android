package com.danbam.indistraw.feature.mobile.navigation.movie

sealed class PeopleType(val route: String) {
    object ACTOR : PeopleType("actor")
    object DIRECTOR : PeopleType("director")
}