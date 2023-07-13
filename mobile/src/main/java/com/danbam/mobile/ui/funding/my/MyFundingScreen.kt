package com.danbam.mobile.ui.funding.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyFundingScreen(
    navController: NavController
) {
    val fundingHeight = LocalConfiguration.current.screenHeightDp * 0.3

    IndiStrawBottomSheetLayout(sheetContent = {

    }) { _, openSheet ->
        IndiStrawColumnBackground(
            scrollEnabled = true
        ) {
            IndiStrawHeader(
                pressBackBtn = { navController.popBackStack() }
            )
            AsyncImage(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(fundingHeight.dp)
                    .fillMaxWidth(),
                model = "",
                contentDescription = "fundingBanner",
                contentScale = ContentScale.Crop
            )
            Divider(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(IndiStrawTheme.colors.darkGray)
            )
            DialogMedium(
                modifier = Modifier.padding(start = 15.dp, bottom = 16.dp), text = stringResource(
                    id = R.string.choose_reward
                )
            )
            repeat(10) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(modifier = Modifier.height(54.dp))
        }
    }
}