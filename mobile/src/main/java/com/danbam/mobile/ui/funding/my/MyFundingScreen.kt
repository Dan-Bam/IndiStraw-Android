package com.danbam.mobile.ui.funding.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.danbam.design_system.IndiStrawTheme
import com.danbam.design_system.R
import com.danbam.design_system.attribute.IndiStrawIcon
import com.danbam.design_system.attribute.IndiStrawIconList
import com.danbam.design_system.component.DialogMedium
import com.danbam.design_system.component.ExampleTextMedium
import com.danbam.design_system.component.FindPasswordMedium
import com.danbam.design_system.component.IndiStrawBottomSheetLayout
import com.danbam.design_system.component.IndiStrawColumnBackground
import com.danbam.design_system.component.IndiStrawHeader
import com.danbam.design_system.component.MyRewardItem
import com.danbam.design_system.component.PriceRegular
import com.danbam.design_system.component.RewardType
import com.danbam.design_system.component.TitleRegular
import com.danbam.design_system.component.TitleSemiBold
import com.danbam.design_system.util.RemoveOverScrollLazyRow
import com.danbam.design_system.util.indiStrawClickable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyFundingScreen(
    navController: NavController
) {
    val fundingHeight = LocalConfiguration.current.screenHeightDp * 0.3
    var isActor by remember { mutableStateOf(false) }

    IndiStrawBottomSheetLayout(sheetContent = {
        if (isActor) {
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 31.dp)
                        .size(70.dp)
                        .clip(IndiStrawTheme.shapes.circle),
                    model = "",
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                FindPasswordMedium(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "",
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
            "".let {
                TitleRegular(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = "(58418)",
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 14
                )
            }
            TitleRegular(
                modifier = Modifier
                    .padding(horizontal = 15.dp),
                text = "" ?: stringResource(id = R.string.no_address),
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
                text = "" ?: stringResource(id = R.string.no_address),
                color = IndiStrawTheme.colors.lightGray,
                fontSize = 14
            )
            Spacer(modifier = Modifier.height(30.dp))
        } else {
//            MyRewardItem(rewardType = RewardType.Expand, item = )
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
                model = "",
                contentDescription = "fundingBanner",
                contentScale = ContentScale.Crop
            )
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                ExampleTextMedium(text = stringResource(id = R.string.money), fontSize = 16)
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = "98%",
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(text = "/", fontSize = 16)
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = "100",
                    color = IndiStrawTheme.colors.lightGray,
                    fontSize = 16
                )
                Spacer(modifier = Modifier.width(2.dp))
                PriceRegular(text = stringResource(id = R.string.money_unit), fontSize = 14)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                ExampleTextMedium(text = stringResource(id = R.string.add_funding), fontSize = 16)
                Spacer(modifier = Modifier.width(4.dp))
                ExampleTextMedium(
                    text = "100",
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
                    text = "D-4",
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
                        text = "120",
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
                modifier = Modifier.padding(start = 15.dp, bottom = 16.dp), text = stringResource(
                    id = R.string.choose_reward
                )
            )
            repeat(10) {
//                MyRewardItem(item = )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Divider(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(IndiStrawTheme.colors.darkGray)
            )
            RemoveOverScrollLazyRow {
                item {
                    Spacer(modifier = Modifier.width(15.dp))
                }
                items(10) {
                    Column(
                        modifier = Modifier.indiStrawClickable(onClick = {
                        }),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(65.dp)
                                .clip(IndiStrawTheme.shapes.circle),
                            model = "",
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )
                        FindPasswordMedium(
                            modifier = Modifier.padding(top = 6.dp),
                            text = ""
                        )
                        TitleRegular(
                            modifier = Modifier.padding(top = 4.dp),
                            text = stringResource(
                                id = 0
                            ),
                            color = IndiStrawTheme.colors.gray,
                            fontSize = 12
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