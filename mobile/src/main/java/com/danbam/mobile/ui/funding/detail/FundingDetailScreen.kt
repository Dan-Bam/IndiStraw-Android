package com.danbam.mobile.ui.funding.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.component.ButtonMedium
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.ExampleTextRegular
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.HeadLineBold
import com.danbam.design_system.component.IndiStrawButton
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.IndiStrawProgress
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.R

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
        FindPasswordMedium(
            modifier = Modifier.padding(start = 15.dp, top = 12.dp),
            text = "${stringResource(id = R.string.mc)}: 이동욱",
            color = IndiStrawTheme.colors.lightGray
        )
        ButtonMedium(modifier = Modifier.padding(15.dp, top = 8.dp), text = "제목")
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 20.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            HeadLineBold(text = "97", fontSize = 18, color = IndiStrawTheme.colors.main)
            ExampleTextMedium(text = "%", fontSize = 12, color = IndiStrawTheme.colors.main)
            Spacer(modifier = Modifier.width(4.dp))
            ExampleTextRegular(
                text = stringResource(id = R.string.complete),
                fontSize = 14,
                color = IndiStrawTheme.colors.main
            )
            Spacer(modifier = Modifier.width(10.dp))
            ExampleTextMedium(
                modifier = Modifier
                    .background(
                        color = IndiStrawTheme.colors.main,
                        shape = IndiStrawTheme.shapes.smallRounded
                    )
                    .padding(horizontal = 4.dp, vertical = 1.dp),
                text = "18${stringResource(id = R.string.date)} ${stringResource(id = R.string.rest)}",
                fontSize = 12
            )
        }
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TitleSemiBold(text = "123,000", fontSize = 18)
            ExampleTextRegular(text = stringResource(id = R.string.money_unit))
            Spacer(modifier = Modifier.width(4.dp))
            ExampleTextRegular(text = stringResource(id = R.string.complete), fontSize = 14)
            Spacer(modifier = Modifier.width(10.dp))
            ExampleTextMedium(
                modifier = Modifier
                    .background(
                        color = IndiStrawTheme.colors.darkGray,
                        shape = IndiStrawTheme.shapes.smallRounded
                    )
                    .padding(horizontal = 4.dp, vertical = 1.dp),
                text = "1,200${stringResource(id = R.string.participate)}",
                color = IndiStrawTheme.colors.lightGray,
                fontSize = 12
            )
        }
        FindPasswordMedium(
            modifier = Modifier.padding(start = 15.dp, top = 7.dp),
            text = stringResource(id = R.string.target_money),
            color = IndiStrawTheme.colors.lightGray
        )
        IndiStrawProgress(modifier = Modifier.padding(top = 12.dp), currentProgress = 50F)
        Divider(
            modifier = Modifier
                .padding(vertical = 28.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.darkGray)
        )
        DialogMedium(
            modifier = Modifier.padding(start = 15.dp, bottom = 16.dp),
            text = stringResource(id = R.string.introduce_project)
        )
        TitleRegular(modifier = Modifier.padding(horizontal = 15.dp), text = "프로젝트 소개 내용")
        HeadLineBold(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp),
            text = stringResource(id = R.string.attached_file),
            fontSize = 16
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 28.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.darkGray)
        )
        DialogMedium(
            modifier = Modifier.padding(start = 15.dp, bottom = 16.dp), text = stringResource(
                id = R.string.choose_reward
            )
        )
        IndiStrawButton(
            modifier = Modifier.padding(top = 37.dp, bottom = 160.dp), text = stringResource(
                id = R.string.do_funding
            )
        ) {

        }
    }
}