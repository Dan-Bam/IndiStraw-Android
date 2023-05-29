package com.danbam.presentation.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.SelectImageButton
import com.danbam.presentation.R
import com.danbam.presentation.util.SignUpNavigationItem

@Composable
fun SetProfileScreen(
    navController: NavController,
) {
    Column {
        IndiStrawHeader(marginTop = 25, backStringId = R.string.back, pressBackBtn = {
            navController.popBackStack()
        })
        HeadLineBold(
            modifier = Modifier
                .padding(start = 32.dp, top = 16.dp),
            text = stringResource(id = R.string.require_picture)
        )
        SelectImageButton(
            modifier = Modifier
                .padding(top = 54.dp)
                .align(CenterHorizontally),
            requireGalleryString = stringResource(id = R.string.choose_gallery),
            requireCameraString = stringResource(id = R.string.choose_camera),
            paddingValues = PaddingValues(36.dp),
            isFirst = true,
            moveGallery = {},
            moveCamera = {}) {
            IndiStrawButton(
                modifier = Modifier.padding(top = 156.dp),
                text = stringResource(id = R.string.next)
            ) {
                navController.navigate(SignUpNavigationItem.SetId.route)
            }
        }
    }
}