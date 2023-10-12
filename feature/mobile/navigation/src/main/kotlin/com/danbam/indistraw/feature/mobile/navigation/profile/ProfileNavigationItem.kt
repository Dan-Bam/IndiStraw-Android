package com.danbam.indistraw.feature.mobile.navigation.profile

sealed class ProfileNavigationItem(val route: String) {
    object Profile : ProfileNavigationItem("profile")
    object Setting : ProfileNavigationItem("setting")
    object EditProfile : ProfileNavigationItem("editProfile")
    object FindAddress : ProfileNavigationItem("findAddress")
    object DetailAddress : ProfileNavigationItem("detailAddress")
    object QRLogin : ProfileNavigationItem("qrLogin")
}