package com.danbam.mobile.ui.movie.make

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader

@Composable
fun AddActorScreen(
    navController: NavController
) {
    IndiStrawColumnBackground {
        IndiStrawHeader(
            pressBackBtn = { navController.popBackStack() }
        )
    }
}