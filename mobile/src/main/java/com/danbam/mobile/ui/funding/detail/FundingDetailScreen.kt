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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.IndiStrawSlider
import com.danbam.design_system.component.RewardItem
import com.danbam.design_system.util.toCommaString
import com.danbam.mobile.util.parser.toCommaString

@Composable
fun FundingDetailScreen(
    navController: NavController,
    fundingIndex: Long,
    fundingDetailViewModel: FundingDetailViewModel = hiltViewModel()
) {
    val container = fundingDetailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val fundingHeight = LocalConfiguration.current.screenHeightDp * 0.3

    LaunchedEffect(Unit) {
        fundingDetailViewModel.getDetail(fundingIndex = fundingIndex)
    }

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
            model = state.fundingDetailEntity.thumbnailUrl,
            contentDescription = "fundingBanner",
            contentScale = ContentScale.Crop
        )
        FindPasswordMedium(
            modifier = Modifier.padding(start = 15.dp, top = 12.dp),
            text = "${stringResource(id = R.string.mc)}: ${state.fundingDetailEntity.writer.name}",
            color = IndiStrawTheme.colors.lightGray
        )
        ButtonMedium(
            modifier = Modifier.padding(15.dp, top = 8.dp),
            text = state.fundingDetailEntity.title
        )
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            HeadLineBold(
                text = state.fundingDetailEntity.amount.percentage.toInt().toString(),
                fontSize = 18,
                color = IndiStrawTheme.colors.main
            )
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
                text = "D-${state.fundingDetailEntity.remainingDay}",
                fontSize = 12
            )
        }
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 9.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TitleSemiBold(
                text = state.fundingDetailEntity.amount.totalAmount.toCommaString(),
                fontSize = 18
            )
            TitleSemiBold(
                text = "/${state.fundingDetailEntity.amount.targetAmount.toCommaString()}",
                fontSize = 18,
                color = IndiStrawTheme.colors.lightGray
            )
            Spacer(modifier = Modifier.width(4.dp))
            ExampleTextRegular(text = stringResource(id = R.string.money_unit))
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .background(
                        color = IndiStrawTheme.colors.darkGray,
                        shape = IndiStrawTheme.shapes.smallRounded
                    )
                    .padding(horizontal = 4.dp, vertical = 1.dp),
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.People)
                Spacer(modifier = Modifier.width(2.dp))
                ExampleTextMedium(
                    text = state.fundingDetailEntity.fundingCount.toCommaString(),
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 12
                )
            }
        }
        IndiStrawProgress(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 20.dp),
            currentProgress = state.fundingDetailEntity.amount.percentage,
            enableText = false
        )
        Divider(
            modifier = Modifier
                .padding(vertical = 28.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(IndiStrawTheme.colors.darkGray)
        )
        TitleRegular(
            modifier = Modifier.padding(horizontal = 15.dp),
            text = state.fundingDetailEntity.description
        )
        IndiStrawSlider(itemCount = state.fundingDetailEntity.imageList.size) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clip(IndiStrawTheme.shapes.defaultRounded),
                model = state.fundingDetailEntity.imageList[it],
                contentDescription = "fundingImage",
                contentScale = ContentScale.Crop
            )
        }
        HeadLineBold(
            modifier = Modifier.padding(start = 15.dp, top = 28.dp),
            text = stringResource(id = R.string.attached_file),
            fontSize = 16
        )
        repeat(state.fundingDetailEntity.fileList.size) {
            Row(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndiStrawIcon(icon = IndiStrawIconList.Attached)
                Spacer(modifier = Modifier.width(8.dp))
                TitleRegular(
                    text = state.fundingDetailEntity.fileList[it],
                    fontSize = 14,
                    color = IndiStrawTheme.colors.skyBlue
                )
            }
        }
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
        repeat(state.fundingDetailEntity.reward.size) {
            RewardItem(item = state.fundingDetailEntity.reward[it])
            Spacer(modifier = Modifier.height(16.dp))
        }
        IndiStrawButton(
            modifier = Modifier.padding(top = 21.dp, bottom = 160.dp), text = stringResource(
                id = R.string.do_funding
            )
        ) {

        }
    }
}