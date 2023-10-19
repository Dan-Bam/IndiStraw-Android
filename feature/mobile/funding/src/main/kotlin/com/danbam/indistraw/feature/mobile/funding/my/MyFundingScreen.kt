package com.danbam.indistraw.feature.mobile.funding.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.danbam.indistraw.core.design_system.IndiStrawTheme
import com.danbam.indistraw.core.design_system.R
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIcon
import com.danbam.indistraw.core.design_system.attribute.IndiStrawIconList
import com.danbam.indistraw.core.design_system.component.ButtonMedium
import com.danbam.indistraw.core.design_system.component.DialogMedium
import com.danbam.indistraw.core.design_system.component.ExampleTextMedium
import com.danbam.indistraw.core.design_system.component.FindPasswordMedium
import com.danbam.indistraw.core.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.indistraw.core.design_system.component.IndiStrawColumnBackground
import com.danbam.indistraw.core.design_system.component.IndiStrawHeader
import com.danbam.indistraw.core.ui.component.MyRewardItem
import com.danbam.indistraw.core.design_system.component.PriceRegular
import com.danbam.indistraw.core.ui.component.RewardType
import com.danbam.indistraw.core.design_system.component.TitleRegular
import com.danbam.indistraw.core.design_system.component.TitleSemiBold
import com.danbam.indistraw.core.design_system.util.androidx.RemoveOverScrollLazyRow
import com.danbam.indistraw.core.design_system.util.androidx.indiStrawClickable
import com.danbam.indistraw.core.design_system.util.internal.toCommaString
import com.danbam.indistraw.core.domain.entity.funding.MyFundingEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyFundingScreen(
    fundingIdx: Long,
    navController: NavController,
    myFundingViewModel: MyFundingViewModel = hiltViewModel()
) {
    val container = myFundingViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val fundingHeight = LocalConfiguration.current.screenHeightDp * 0.3
    var isActor by remember { mutableStateOf(false) }
    var selectedActor: MyFundingEntity.OrderEntity? by remember { mutableStateOf(null) }
    var selectedReward: MyFundingEntity.RewardEntity? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        myFundingViewModel.myDetail(fundingIdx = fundingIdx)
    }

    IndiStrawBottomSheetLayout(sheetContent = {
        if (isActor) {
            selectedActor?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (it.profileUrl != null) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(top = 31.dp)
                                .size(70.dp)
                                .clip(IndiStrawTheme.shapes.circle),
                            model = it.profileUrl,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(
                                    color = IndiStrawTheme.colors.gray,
                                    shape = IndiStrawTheme.shapes.circle
                                )
                        ) {
                            IndiStrawIcon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center),
                                icon = IndiStrawIconList.Profile,
                            )
                        }
                    }
                    FindPasswordMedium(
                        modifier = Modifier.padding(top = 4.dp),
                        text = it.name,
                        fontSize = 14
                    )
                }
                Divider(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(IndiStrawTheme.colors.darkGray)
                )
                TitleSemiBold(
                    modifier = Modifier.padding(start = 15.dp),
                    text = stringResource(id = R.string.address)
                )
                it.zipCode?.let {
                    TitleRegular(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        text = "(${it})",
                        color = IndiStrawTheme.colors.lightGray,
                        fontSize = 14
                    )
                }
                TitleRegular(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    text = it.address ?: stringResource(id = R.string.no_address),
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 14
                )
                Divider(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(IndiStrawTheme.colors.darkGray)
                )
                TitleSemiBold(
                    modifier = Modifier.padding(start = 15.dp),
                    text = stringResource(id = R.string.phone_number)
                )
                TitleRegular(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    text = it.phoneNumber,
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 14
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        } else {
            selectedReward?.let {
                com.danbam.indistraw.core.ui.component.MyRewardItem(
                    rewardType = com.danbam.indistraw.core.ui.component.RewardType.Expand,
                    item = it
                ) {

                }
                Spacer(modifier = Modifier.height(45.dp))
            }
        }
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
                model = state.myFundingEntity.thumbnailUrl,
                contentDescription = "fundingBanner",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            ButtonMedium(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = state.myFundingEntity.title
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                ExampleTextMedium(text = stringResource(id = R.string.money), fontSize = 16)
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = "${"%.1f".format(state.myFundingEntity.amount.percentage)}%",
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(text = "/", fontSize = 16)
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = state.myFundingEntity.amount.totalAmount.toCommaString(),
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(2.dp))
                PriceRegular(text = stringResource(id = R.string.money_unit), fontSize = 14)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                ExampleTextMedium(
                    text = stringResource(id = R.string.add_funding),
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = state.myFundingEntity.amount.totalAmount.toCommaString(),
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(2.dp))
                PriceRegular(text = stringResource(id = R.string.money_unit), fontSize = 14)
                Spacer(modifier = Modifier.width(12.dp))
                ExampleTextMedium(
                    modifier = Modifier
                        .background(
                            color = IndiStrawTheme.colors.main,
                            shape = IndiStrawTheme.shapes.smallRounded
                        )
                        .padding(horizontal = 4.dp, vertical = 1.dp),
                    text = "D-${state.myFundingEntity.remainingDay}",
                    fontSize = 12
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier
                        .background(
                            IndiStrawTheme.colors.gray3,
                            IndiStrawTheme.shapes.smallRounded
                        )
                        .padding(horizontal = 5.dp, vertical = 1.dp)
                ) {
                    IndiStrawIcon(icon = IndiStrawIconList.People)
                    PriceRegular(
                        text = state.myFundingEntity.fundingCount.toCommaString(),
                        fontSize = 12,
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(IndiStrawTheme.colors.darkGray)
            )
            DialogMedium(
                modifier = Modifier.padding(start = 15.dp, bottom = 16.dp),
                text = stringResource(
                    id = R.string.choose_reward
                )
            )
            repeat(state.myFundingEntity.reward.size) { index ->
                com.danbam.indistraw.core.ui.component.MyRewardItem(item = state.myFundingEntity.reward[index]) {
                    isActor = false
                    selectedReward = it
                    openSheet()
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Divider(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(IndiStrawTheme.colors.darkGray)
            )
            if (state.myFundingEntity.orderList.isNotEmpty()) {
                DialogMedium(
                    modifier = Modifier.padding(start = 15.dp),
                    text = stringResource(id = R.string.participate_people)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            RemoveOverScrollLazyRow {
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
                items(state.myFundingEntity.orderList) {
                    Column(
                        modifier = Modifier.indiStrawClickable(onClick = {
                            isActor = true
                            selectedActor = it
                            openSheet()
                        }),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (it.profileUrl != null) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(65.dp)
                                    .clip(IndiStrawTheme.shapes.circle),
                                model = it.profileUrl,
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(65.dp)
                                    .background(
                                        color = IndiStrawTheme.colors.gray,
                                        shape = IndiStrawTheme.shapes.circle
                                    )
                            ) {
                                IndiStrawIcon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.Center),
                                    icon = IndiStrawIconList.Profile,
                                )
                            }
                        }
                        TitleSemiBold(
                            modifier = Modifier.padding(top = 6.dp),
                            text = it.name
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}