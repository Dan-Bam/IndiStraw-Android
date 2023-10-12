package com.danbam.indistraw.feature.mobile.navigation.auth

sealed class AuthNavigationItem(val route: String) {
    object Login : AuthNavigationItem("login")
    object Certificate : AuthNavigationItem("certificate")
    object FindId : AuthNavigationItem("findId")
    object FindPassword : AuthNavigationItem("findPassword")
    object SetName : AuthNavigationItem("setName")
    object SetProfile : AuthNavigationItem("setProfile")
    object SetId : AuthNavigationItem("setId")
    object SetPassword : AuthNavigationItem("setPassword")
}