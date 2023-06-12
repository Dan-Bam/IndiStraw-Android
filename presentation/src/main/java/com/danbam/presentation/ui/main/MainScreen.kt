package com.danbam.presentation.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawBanner
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.Shape
import com.danbam.presentation.util.view.AppNavigationItem
import com.danbam.presentation.util.view.findActivity
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    navController: NavController,
) {
    val context = LocalContext.current

    BackHandler {
        context.findActivity()?.finish()
    }

    IndiStrawColumnBackground {
        IndiStrawHeader(
            isBackBtn = false
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Search)
                ImageButton(
                    modifier = Modifier
                        .padding(start = 26.dp)
                        .width(30.dp)
                        .height(30.dp),
                    imgSrc = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
                    shape = Shape.Circle
                ) {
                    navController.navigate(AppNavigationItem.Main.route)
                }
            }
        }
        IndiStrawBanner(itemCount = 4) {
            Text(text = "안녕 $it")
        }
    }
}