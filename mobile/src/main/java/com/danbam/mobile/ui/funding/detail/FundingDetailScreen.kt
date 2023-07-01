package com.danbam.mobile.ui.funding.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.ImageButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawProgress
import com.danbam.design_system.component.Shape
import com.danbam.design_system.component.TitleRegular
import com.danbam.mobile.ui.movie.navigation.MovieNavigationItem

@Composable
fun FundingDetailScreen(
    navController: NavController
) {
    val fundingHeight = LocalConfiguration.current.screenHeightDp * 0.3
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
            model = "https://media.discordapp.net/attachments/823502916257972235/1111432831089000448/IMG_1218.png?width=1252&height=1670",
            contentDescription = "fundingBanner",
            contentScale = ContentScale.Crop
        )
        FindPasswordMedium(text = "진행자: 이동욱")
        ButtonMedium(text = "제목")
        Row {

        }
        Row {

        }
        FindPasswordMedium(text = "")
        IndiStrawProgress(currentProgress = 50F)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.darkGray)
        )
        DialogMedium(text = "프로젝트 소개")
        TitleRegular(text = "프로젝트 소개 내용")

    }
}